package biz.deinum.samples.configurations.xml;

import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by marten on 17-01-17.
 */
public class BasicMain {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/applicationContext-basic.xml");

        HelloWorld helloWorld = beanFactory.getBean("hello", HelloWorld.class);
        helloWorld.sayHello();

        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("BeanFactory contains " + names.length + " beans.");
        for (String name : names) {
            System.out.println("Bean name: " + name + " | " + beanFactory.getBean(name));
        }

    }
}
