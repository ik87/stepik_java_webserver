package server_services.servlets;

import server_services.AccountServiceImpl;
import db_services.data_set.UserDataSet;

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
    private AccountServiceImpl accountServiceImpl;

    public SignUpServlet(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (accountServiceImpl.signUp(login, password)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
