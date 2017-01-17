package biz.deinum.samples.configurations.java;

import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marten on 17-01-17.
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public HelloWorld hello() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setName("Marten!");
        return helloWorld;
    }
}
