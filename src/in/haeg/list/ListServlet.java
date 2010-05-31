package in.haeg.list;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial") public class ListServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
            PersistenceManager pm = PMF.get().getPersistenceManager();
            String title = req.getParameter("title");
            Text content = new Text(req.getParameter("list"));
            Note list = new Note(userService.getCurrentUser(), title, content);
            pm.makePersistent(list);
        }

        resp.sendRedirect("/list");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/list");
    }

}
