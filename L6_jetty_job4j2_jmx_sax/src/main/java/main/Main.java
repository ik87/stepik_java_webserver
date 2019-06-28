package main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resource_servers.ResourceController;
import resource_servers.ResourceControllerMBean;
import resource_servers.ResourceServer;
import resource_servers.ResourceServerImpl;
import servlets.ResourcePageServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Get instance through xml with use reflection and sax
 * Check result with use JMX (jconsole)
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

        ResourceServer resourceServer = new ResourceServerImpl();

        ResourceControllerMBean resourceController = new ResourceController(resourceServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceController, name);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder( new ResourcePageServlet(resourceServer)), "/resource");

        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        LOG.info("Server started");
        server.join();

    }
}
