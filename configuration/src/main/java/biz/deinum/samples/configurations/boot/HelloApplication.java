package biz.deinum.samples.configurations.boot;

import biz.deinum.samples.configurations.BeanFactoryHelper;
import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by marten on 17-01-17.
 */
@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HelloApplication.class, args);

        BeanFactoryHelper.sayHello(context);
        BeanFactoryHelper.statistics(context);
    }

    @Bean
    public HelloWorld hello() {

        HelloWorld hello = new HelloWorld();
        hello.setName("Marten!");
        return hello;
    }
}
