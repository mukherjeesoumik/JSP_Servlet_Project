package com.soumikservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloservlet")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String rollnumber = request.getParameter("rollnumber");
        String gender = request.getParameter("gender");
        String countrycode = request.getParameter("countrycode");
        String mobilenumber = request.getParameter("mobilenumber");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registration Details</title>");
        out.println("<style>");
        out.println("body { font-family: 'Montserrat', sans-serif; background: linear-gradient(135deg, #71b7e6, #9b59b6); color: #333; text-align: center; padding: 50px; }");
        out.println(".container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1); display: inline-block; width: 600px; }");
        out.println("h1 { margin-bottom: 20px; color: #9b59b6; }");
        out.println("p { margin: 10px 0; font-size: 16px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Registration Details</h1>");
        out.println("<p><strong>Name:</strong> " + name + "</p>");
        out.println("<p><strong>Age:</strong> " + age + "</p>");
        out.println("<p><strong>Gender:</strong> " + gender + "</p>");
        out.println("<p><strong>Mobile Number:</strong> " + countrycode + " " + mobilenumber + "</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
