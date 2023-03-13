package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        var companies = getCompanies();
        var searchString = request.getParameter("search") == null
                ? ""
                : request.getParameter("search");
        var out = response.getWriter();
        var filteredCompanies = companies.stream().filter(company -> company.contains(searchString)).toList();

        if (filteredCompanies.isEmpty()) {
            out.println("Companies not found");
            return;
        }

        filteredCompanies.forEach(out::println);
        // END
    }
}
