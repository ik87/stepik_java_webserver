package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebsocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket());
    }
}
