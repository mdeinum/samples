package biz.deinum.samples.configurations;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.util.Assert;

/**
 * Created by marten on 17-01-17.
 */
public class BeanFactoryHelper {

    public static void sayHello(BeanFactory factory) {
        HelloWorld helloWorld = factory.getBean("hello", HelloWorld.class);
        helloWorld.sayHello();
    }

    public static void statistics(BeanFactory factory) {
        Assert.state(factory instanceof ListableBeanFactory);
        ListableBeanFactory beanFactory = (ListableBeanFactory) factory;
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("BeanFactory " + factory + " contains " + names.length + " beans.");
        for (String name : names) {
            System.out.println("Bean name: " + name + " | " + beanFactory.getBean(name));
        }

    }

}
