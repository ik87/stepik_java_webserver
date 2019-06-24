package main;


import account_servers.AccountServerController;
import account_servers.AccountServerControllerMBean;
import account_servers.AccountServerImpl;
import account_servers.AccountServers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AdminPageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Easy test JMX controller through jconsole
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            LOG.error("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        int port = Integer.valueOf(portString);

        AccountServers accountServer = new AccountServerImpl(10);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder( new AdminPageServlet(accountServer)), "/admin");

        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        LOG.info("Server started");
        server.join();

    }
}
