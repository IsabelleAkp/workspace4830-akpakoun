
/**
 * @file FormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormInsert")
public class FormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public FormInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String animal = request.getParameter("animal");
      String diet = request.getParameter("diet");
      String food = request.getParameter("food");

      Connection connection = null;
      String insertSql = " INSERT INTO myTable2 (id, animal, diet, food) values (default, ?, ?, ?)";

      try {
         DBConnection.getDBConnection(getServletContext());
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, animal);
         preparedStmt.setString(2, diet);
         preparedStmt.setString(3, food);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to the database table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#abf7b1\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //
            
            "  <li align=\"center\"><b>Animal</b>: " + animal + "\n\n" + //
            "  <li align=\"center\"><b>Diet</b>: " + diet + "\n\n" + //
            "  <li align=\"center\"><b>Food</b>: " + food + "\n\n" + //

            "</ul>\n");

      out.println("<a href=/dynamicProj/FormSearch.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
