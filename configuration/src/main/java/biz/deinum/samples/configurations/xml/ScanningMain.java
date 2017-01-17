package biz.deinum.samples.configurations.xml;

import biz.deinum.samples.configurations.BeanFactoryHelper;
import biz.deinum.samples.configurations.HelloWorld;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Created by marten on 17-01-17.
 */
public class ScanningMain {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/applicationContext-scanning.xml");


        BeanFactoryHelper.sayHello(beanFactory);
        BeanFactoryHelper.statistics(beanFactory);

    }
}
