package exercise.servlet;

import exercise.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static exercise.App.getUsers;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        final var session = request.getSession();
        final var email = request.getParameter("email");
        final var password = request.getParameter("password");
        final var user = getUsers().findByEmail(email);
        final var requestDispatcher = request.getRequestDispatcher("/login.jsp");

        if (user == null || !Objects.equals(user.get(password), password)) {
            session.setAttribute("flash", "Неверные логин или пароль");
            request.setAttribute("user", new Users().build(email));
            response.setStatus(422);
            requestDispatcher.forward(request, response);
            return;
        }

        session.setAttribute("userId", user.get("id"));
        session.setAttribute("flash", "Вы успешно вошли");
        response.sendRedirect("/");
        // END
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        // BEGIN
        final var session = request.getSession();
        session.removeAttribute("userId");
        session.setAttribute("flash", "Вы успешно вышли");
        response.sendRedirect("/");
        // END
    }
}
