package resource_servers;

import resources.TestResource;

public class ResourceServerImpl implements ResourceServer {
    private TestResource testResource;

    @Override
    public void setTestResource(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }

}
