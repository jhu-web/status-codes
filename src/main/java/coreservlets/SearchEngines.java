package coreservlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that takes a search string and a search engine name, sending the query to that
 * search engine. Illustrates manipulating the response status code. It sends a 302
 * response (via sendRedirect) if it gets a known search engine, and sends a 404 response
 * (via sendError) otherwise.
 * <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com
 * tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
@WebServlet("/search-engines")
public class SearchEngines
  extends HttpServlet {

  private static final long serialVersionUID = -1566072412959839771L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String searchString = request.getParameter("searchString");
    if ((searchString == null) || (searchString.length() == 0)) {
      reportProblem(response, "Missing search string");
      return;
    }
    // The URLEncoder changes spaces to "+" signs and other
    // non-alphanumeric characters to "%XY", where XY is the
    // hex value of the ASCII (or ISO Latin-1) character.
    // Browsers always URL-encode form values, and the
    // getParameter method decodes automatically. But since
    // we're just passing this on to another server, we need to
    // re-encode it to avoid characters that are illegal in
    // URLs.
    searchString = URLEncoder.encode(searchString, "utf-8");
    String searchEngineName = request.getParameter("searchEngine");
    if ((searchEngineName == null) || (searchEngineName.length() == 0)) {
      reportProblem(response, "Missing search engine name");
      return;
    }
    String searchURL = SearchUtilities.makeURL(searchEngineName, searchString);
    if (searchURL != null) {
      response.sendRedirect(searchURL);
    }
    else {
      reportProblem(response, "Unrecognized search engine");
    }
  }

  private void reportProblem(HttpServletResponse response, String message)
    throws IOException {
    response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
  }
}
