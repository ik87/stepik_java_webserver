package resource_servers;

import resources.TestResource;

public interface ResourceServer {
    void setTestResource(TestResource testResource);

    String getName();

    int getAge();
}
