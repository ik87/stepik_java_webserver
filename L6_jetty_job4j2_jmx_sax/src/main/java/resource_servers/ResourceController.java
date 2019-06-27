package resource_servers;

public class ResourceController implements ResourceControllerMBean {
    private ResourceServer resourceServer;

    public ResourceController(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    public String getName() {
        return resourceServer.getName();
    }

    @Override
    public int getAge() {
        return resourceServer.getAge();
    }
}
