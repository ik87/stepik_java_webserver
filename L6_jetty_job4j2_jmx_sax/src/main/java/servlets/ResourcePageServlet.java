package servlets;

import resource_servers.ResourceServer;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourcePageServlet extends HttpServlet {
    private ResourceServer resourceServer;

    public ResourcePageServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        TestResource testResource = (TestResource) ReadXMLFileSAX.readXML(path);
        if(testResource != null) {
            resourceServer.setTestResource(testResource);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }


    }
}
