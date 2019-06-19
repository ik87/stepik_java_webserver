package server_services.servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Defined servlet that gets post request and add new user profile
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UserProfile userProfile = accountService.getUser(login);
        if (userProfile != null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            accountService.addNewUser(new UserProfile(login, password));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
