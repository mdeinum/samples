package biz.deinum.samples.configurations.properties;

import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by marten on 17-01-17.
 */
public class Main {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:application-context.properties");

        HelloWorld helloWorld = beanFactory.getBean("hello", HelloWorld.class);
        helloWorld.sayHello();

        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("BeanFactory contains " + names.length + " beans.");
        for (String name : names) {
            System.out.println("Bean name: " + name + " | " + beanFactory.getBean(name));
        }
    }
}
