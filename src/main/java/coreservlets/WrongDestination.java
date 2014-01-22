package coreservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that sends IE users to the Mozilla home page and Firefox (and all other) users
 * to the Microsoft home page.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com
 * tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
@WebServlet("/wrong-destination")
public class WrongDestination
  extends HttpServlet {

  private static final long serialVersionUID = -2647067708287435434L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String userAgent = request.getHeader("User-Agent");
    if ((userAgent != null) && (userAgent.contains("MSIE"))) {
      response.sendRedirect("http://www.mozilla.com/");
    }
    else {
      response.sendRedirect("http://www.microsoft.com/");
    }
  }
}
