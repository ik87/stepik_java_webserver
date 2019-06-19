package main;

import base.DbServices;
import db_services.DBServiceImpl;
import server_services.AccountServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server_services.servlets.SignInServlet;
import server_services.servlets.SignUpServlet;

/**
 * Easy authorization
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DbServices dbServices = new DBServiceImpl();
        AccountServiceImpl accountServiceImpl = new AccountServiceImpl(dbServices);
        dbServices.create();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(accountServiceImpl)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(accountServiceImpl)), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();

    }
}
