package servlets;

import account_servers.AccountServers;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class AdminPageServlet extends HttpServlet {
    private AccountServers accountServer;

    public AdminPageServlet(AccountServers accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(accountServer.getLimit());
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
