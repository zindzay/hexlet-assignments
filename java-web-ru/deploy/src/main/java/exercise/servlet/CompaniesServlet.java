package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        var companies = getCompanies();
        var searchString = request.getParameter("search");
        var writer = response.getWriter();

        if (searchString == null || searchString.isEmpty()) {
            companies.forEach(writer::println);
            return;
        }

        companies.stream().filter(company -> company.contains(searchString)).forEach(writer::println);
        // END
    }
}
