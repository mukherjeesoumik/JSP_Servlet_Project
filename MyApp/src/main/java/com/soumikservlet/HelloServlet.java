package com.soumikservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HelloServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>All Records</title>");
        out.println("<style>");
        out.println("body { font-family: 'Montserrat', sans-serif; background: linear-gradient(135deg, #71b7e6, #9b59b6); color: #333; text-align: center; padding: 50px; }");
        out.println(".container { background: white; padding: 40px; border-radius: 12px; box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1); display: inline-block; width: 600px; }");
        out.println("h1 { margin-bottom: 20px; color: #9b59b6; }");
        out.println("p { margin: 10px 0; font-size: 16px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>All Records</h1>");

        String jdbcURL = "jdbc:postgresql://localhost:5432/employee";
        String dbUser = "postgres";
        String dbPassword = "123";

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                showAllRecords(connection, out);
            }
        } catch (ClassNotFoundException e) {
            out.println("<p>PostgreSQL JDBC Driver not found. Include it in your library path.</p>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            e.printStackTrace(out); // Print error details to the response
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    private void showAllRecords(Connection connection, PrintWriter out) throws SQLException {
        String sql = "SELECT * FROM users";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                out.println("<p><strong>ID:</strong> " + resultSet.getString("id") + "</p>");
                out.println("<p><strong>Name:</strong> " + resultSet.getString("name") + "</p>");
                out.println("<p><strong>Age:</strong> " + resultSet.getInt("age") + "</p>");
                out.println("<p><strong>Gender:</strong> " + resultSet.getString("gender") + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + resultSet.getString("mobilenumber") + "</p>");
                out.println("<hr>");
            }
        }
    }
}
