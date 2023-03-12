package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// BEGIN
public final class WelcomeServlet extends HttpServlet {
    public static final String HELLO_MESSAGE = "Hello, Hexlet!";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        pw.write(HELLO_MESSAGE);
        pw.close();
    }
}
// END
