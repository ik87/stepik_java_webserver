package server_services.servlets;

import base.UserProfile;
import server_services.AccountServiceImpl;
import db_services.data_set.UserDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Defined servlet that gets post request and check exists or not the user
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class SignInServlet extends HttpServlet {
    private AccountServiceImpl accountServiceImpl;

    public SignInServlet(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (accountServiceImpl.signIn(login, password)) {
            resp.getWriter().println("Authorized: " + login);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("Unauthorized");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
