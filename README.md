# JSP ( Java Server Pages )
JavaServer Pages (JSP) is a server-side technology that allows developers to create dynamic, platform-independent web applications. JSP is part of the Java EE (Enterprise Edition) platform and is an extension of the servlet technology.
=> download file ( apache-tomcat-10.1.34 )
<img src="https://github.com/mukherjeesoumik/mukherjeesoumik/blob/main/0000.jpeg?raw=true" width="1920">
# Employee Management System :



``` bash
MyApp
├── src
│   └── main
│       ├── java
│       │   └── com.soumikservlet
│       │        │
│       │        └── HelloServlet.java
│       └── webapp
│           ├── WEB-INF
│           │   └── web.xml
│           └── index.jsp
├── pom.xml

```
#pom.xml

```cs

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.example</groupId>
  <artifactId>MyApp</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>MyApp Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>


    </dependency>

                             <!-- Jakarta Servlet API -->

    <!-- https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api -->
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>6.0.0</version>
      <scope>provided</scope>
    </dependency>

                             <!-- PostgreSQL JDBC Driver -->
    
    <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
    <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
      <version>42.6.0</version>
    </dependency>


  </dependencies>


  <build>
    <finalName>MyApp</finalName>
  </build>
</project>

```
#HelloServlet.java

```cs

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


```
#index.jsp

```cs
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap');

        body {
            font-family: 'Montserrat', sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        .container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            text-align: left;
            margin-bottom: 5px;
            font-weight: 600;
            color: #555;
        }

        input, select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
            transition: border-color 0.3s;
        }

        input:focus, select:focus {
            border-color: #9b59b6;
        }

        button {
            padding: 12px;
            border: none;
            border-radius: 6px;
            background-color: #28a745; /* Green color */
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838; /* Darker green on hover */
        }

        .mobile-input-group {
            display: flex;
            gap: 5px;
        }

        .country-code {
            width: 90px;
        }

        .mobile-number {
            flex-grow: 1;
        }

        .gender-group {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .gender-group label {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Registration Form</h1>
    <form action="hello" method="POST">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required>

        <label for="gender">Gender:</label>
        <div class="gender-group">
            <input type="radio" id="male" name="gender" value="male" required>
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="female" required>
            <label for="female">Female</label>
        </div>

        <label for="mobilenumber">Mobile Number:</label>
        <div class="mobile-input-group">
            <input type="tel" class="mobile-number" id="mobilenumber" name="mobilenumber" required>
        </div>

        <label for="action">Action:</label>
        <select id="action" name="action" required>
            <option value="create">Create</option>
            <option value="update">Update</option>
            <option value="delete">Delete</option>
            <option value="show">Show</option>
            <option value="showall">Show All</option>
        </select>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>

```

#web.xml

```cs
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
</web-app>


```

#Database Table (create database in postgresql) --> *DATABASE NAME=> employee
```cs
CREATE TABLE users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    gender VARCHAR(255),
    mobilenumber VARCHAR(255)
);
```
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Install Tomcat Server
```cs
Step 1: Install Tomcat Server
Download and Install Tomcat: Download the Tomcat server from the official website and install it on your machine.

Set Up Environment Variables: Set up the CATALINA_HOME and JAVA_HOME environment variables to point to your Tomcat and Java installations, respectively.

Step 2: Install IntelliJ IDEA Plugin
Open IntelliJ IDEA: Launch IntelliJ IDEA.

Install Tomcat Plugin: Go to File > Settings > Plugins. Search for "Tomcat" and install the Tomcat plugin2.

Step 3: Configure Tomcat Server in IntelliJ IDEA
Open Settings: Go to File > Settings.

Navigate to Tomcat: Go to Build, Execution, Deployment > Application Servers.

Add New Server: Click the + button and select Tomcat Server.

Configure Server: Set the Application server to the path where Tomcat is installed.

Save Configuration: Click OK to save the configuration.

Step 4: Create a New Project or Open an Existing One
Create a New Project: Go to File > New > Project and select Web Application.

Open Existing Project: Open your existing project if you already have one.

Step 5: Deploy Your Application to Tomcat
Open Project Structure: Go to File > Project Structure.

Set Deployment Configuration: Go to Project: [Your Project Name] > Project Bases and set the deployment configuration to the Tomcat server you configured.

Deploy Application: Right-click on your project in the Project view and select Run > Run 'Tomcat Server'.

Step 6: Verify Deployment
Start Tomcat Server: Ensure the Tomcat server is running.

Access Application: Open your web browser and navigate to http://localhost:8080/YourAppName to verify that your application is deployed and running correctly.
```

<a id='ssFeatures'>Soumik Mukherjee</a>
