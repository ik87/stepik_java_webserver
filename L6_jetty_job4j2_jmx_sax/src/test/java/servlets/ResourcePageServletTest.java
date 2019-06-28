package servlets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;
import resource_servers.ResourceServerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourcePageServletTest {

    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        return response;
    }

    private HttpServletRequest getMockedRequest(String url) {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getPathInfo()).thenReturn(url);

        return request;
    }

    @Test
    public void whenPutXmlThenGetData() throws Exception {
        ResourceServerImpl resourceServer = new ResourceServerImpl();
        ResourcePageServlet resourcePageServlet = new ResourcePageServlet(resourceServer);
        final StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedRequest("/resource");
        String path = this.getClass().getResource("/data/test.xml").getPath();

        when(request.getParameter("path")).thenReturn(path);


        resourcePageServlet.doPost(request, response);

        assertThat(resourceServer.getAge(), is(678));
        assertThat(resourceServer.getName(), is("Test1456776582460"));

    }
}
