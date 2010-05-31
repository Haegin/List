package in.haeg.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial") public class MainServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        boolean loggedIn = false;

        writer.write(GenericHTML.header("Li&#64262;"));

        /* User Login/Logout */
        UserService userService = UserServiceFactory.getUserService();
        String thisURL = req.getRequestURI();
        if (req.getUserPrincipal() != null) {
            writer.println("<div id='logout'><a href='" + userService.createLogoutURL(thisURL) + "'>Sign Out</a></div>");
            loggedIn = true; // Remember the user is logged in
        } else {
            writer.println("<div id='login'><a href='" + userService.createLoginURL(thisURL) + "'>Sign In</a></div>");
        }

        writer.println("<div id='content'>");
        writer.println("<h1>Li&#64262;</h1>");

        if (loggedIn) {
            writer.println("<div id='container'>");

            User user = userService.getCurrentUser();
            PersistenceManager pm = PMF.get().getPersistenceManager();
            Query query = pm.newQuery(Note.class);
            query.setFilter("m_User == userParam");
            query.setOrdering("m_Name desc");
            query.declareImports("import com.google.appengine.api.users.User");
            query.declareParameters("User userParam");

            List<Note> results = (List<Note>) query.execute(user);

            writer.println("<ul id='lists'>");
            for (Note note : results) {
                writer.println("<li><a href='#" + note.getKey() + "'>" + note.getName() + "</a></li>");
            }
            writer.println("</ul>");

            writer.println("<form id='add-list-form' action='/add-list' method='post'>");
            writer.println("<div><label for='title'>Title</label><input class='form-text' id='title' type='text' name='title'></input></div>");
            writer.println("<div><label for='list'>List</label><textarea class='form-text' cols=30 rows=8 name='list' id='list'></textarea></div>");
            writer.println("<div><input type='submit' value='Save' name='save'></input></div>");
            writer.println("</form>");
            writer.println("</div>");

        } else {
            writer.println("<p>You need to sign up to use Li&#64262;'s.</p>");
        }
        writer.println("</div>");

        writer.write(GenericHTML.footer());
    }
}
