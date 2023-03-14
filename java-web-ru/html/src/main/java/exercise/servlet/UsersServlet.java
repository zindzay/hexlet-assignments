package exercise.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {
    static final String HTML_HEADER = """
            <!DOCTYPE html>
            <html lang=\"ru\">
                <head>
                    <meta charset=\"UTF-8\">
                    <title>Example application | Users</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                     rel="stylesheet"
                     integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                     crossorigin="anonymous">
                </head>
                <body>
            """;
    static final String HTML_FOOTER = """
                </body>
            </html>
            """;


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        var mapper = new ObjectMapper();
        return mapper.readValue(new File("src/main/resources/users.json"), new TypeReference<>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        var out = response.getWriter();
        out.println(HTML_HEADER + createUserTableHtml(getUsers()) + HTML_FOOTER);

        // END
    }

    private String createUserTableHtml(List<User> users) {
        var sb = new StringBuilder();

        sb.append("<table>");
        for (var user : users) {
            sb.append(createUserTableRowHtml(user));
        }
        sb.append("</table>");

        return sb.toString();
    }

    private String createUserLinkHtml(User user) {
        return "<a href=\"/users/" + user.id() + ">" + user.firstName() + " " + user.lastName() + "</a>";
    }

    private String createUserTableRowHtml(User user) {
        return "<tr><td>" + user.id() + "</td>"
                + "<td>" + createUserLinkHtml(user) + "</td>"
                + "<td>" + user.email() + "</td></tr>";
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        var user = getUsers().stream().filter(it -> id.equals(it.id())).findFirst().orElse(null);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        var out = response.getWriter();
        out.println(createUserTableHtml(List.of(user)));
        // END
    }
}
