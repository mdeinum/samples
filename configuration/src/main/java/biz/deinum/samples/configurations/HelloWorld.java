package biz.deinum.samples.configurations;

import org.springframework.stereotype.Component;

/**
 * Created by marten on 17-01-17.
 */
@Component("hello")
public class HelloWorld {

    private static final String DEFAULT_NAME = "World!";

    private String name = DEFAULT_NAME;

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, " + name);
    }
}
