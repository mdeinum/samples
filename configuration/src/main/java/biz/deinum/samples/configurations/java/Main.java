package biz.deinum.samples.configurations.java;

import biz.deinum.samples.configurations.BeanFactoryHelper;
import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/**
 * Created by marten on 17-01-17.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        BeanFactoryHelper.sayHello(context);
        BeanFactoryHelper.statistics(context);
    }
}
