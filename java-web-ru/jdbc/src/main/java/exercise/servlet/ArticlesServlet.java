package exercise.servlet;

import exercise.TemplateEngineUtil;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private int getPage(HttpServletRequest request) {
        final var page = request.getParameter("page");
        return page == null ? 1 : Integer.parseInt(page);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {

        final ServletContext context = request.getServletContext();
        final Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        final int page = getPage(request);
        final var nextPage = page + 1;
        final var prevPage = page > 1 ? page - 1 : page;
        final var articlesPerPage = 10;
        final var offset = page * 10 - 10;
        final List<Map<String, String>> articles = new ArrayList<>();
        final var query = "SELECT id, title, body FROM articles ORDER BY id LIMIT ? OFFSET ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, articlesPerPage);
            statement.setInt(2, offset);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                articles.add(Map.of(
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("articles", articles);
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        final ServletContext context = request.getServletContext();
        final Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        final var id = Integer.parseInt(getId(request));
        final var query = "SELECT id, title, body FROM articles WHERE id = ?";
        final Map<String, String> article = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (!rs.first()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            article.put("title", rs.getString("title"));
            article.put("body", rs.getString("body"));
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
