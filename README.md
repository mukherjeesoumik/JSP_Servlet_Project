# JSP Servlet (Project-Tomcat), (jakarta.servlet)
JSP (JavaServer Pages)
JavaServer Pages (JSP) is a server-side technology that allows developers to create dynamic, platform-independent web applications. JSP is part of the Java EE (Enterprise Edition) platform and is an extension of the servlet technology.

Key Features:
Separation of Concerns: JSP separates the business logic from the presentation layer. This is achieved by embedding Java code in HTML pages using special JSP tags.

Simplicity: JSP is easy to use for those familiar with HTML and Java. It simplifies the creation of dynamic web content.

Tag Libraries: JSP supports custom tag libraries, which allow developers to create reusable components and reduce code duplication.

Integration with Servlets: JSP pages can work seamlessly with servlets, enabling powerful and flexible web applications.

Example:
jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome to JSP!</h1>
    <p>Current Date and Time: <%= new java.util.Date() %></p>
</body>
</html>
Servlets
Servlets are Java programming language classes that extend the capabilities of servers that host applications accessed via a request-response programming model. Servlets can respond to any type of request, but they are most commonly used to extend the applications hosted by web servers.

Key Features:
Request-Response Model: Servlets operate under a request-response model, handling client requests and returning appropriate responses.

Lifecycle Management: The servlet lifecycle is managed by the servlet container, which includes initialization, request handling, and termination.

Platform Independence: Servlets are written in Java, making them platform-independent and portable across different operating systems.

Scalability: Servlets can handle multiple requests concurrently and can be scaled to meet increasing demand.

Example:
java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Welcome to Servlets!</h1>");
        out.println("</body></html>");
    }
}
Integration of JSP and Servlets
JSP and servlets often work together in a web application. The servlet handles the business logic and processes the client requests, while the JSP page generates the response content.

Workflow:
Client Request: The client sends a request to the server.

Servlet Processing: The servlet processes the request, performs any necessary business logic, and sets attributes in the request object.

Forwarding to JSP: The servlet forwards the request to a JSP page for rendering.

JSP Rendering: The JSP page uses the attributes set by the servlet to generate the dynamic content and sends the response back to the client.

Example Integration:
Servlet Code:
java
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentDate", new java.util.Date());
        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}
JSP Code (welcome.jsp):
jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome to JSP and Servlets!</h1>
    <p>Current Date and Time: <%= request.getAttribute("currentDate") %></p>
</body>
</html>
Together, JSP and servlets form a powerful combination for building dynamic and interactive web applications in Java.
