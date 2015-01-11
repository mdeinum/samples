package biz.deinum.sample.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by marten on 18-02-14.
 */
@ImportResource("classpath:spring-factory.xml")
@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        SimpleService service = ctx.getBean(SimpleService.class);
        SimpleRepository r1 = service.getRepository();
        SimpleRepository r2 = ctx.getBean(SimpleRepository.class);
        logger.info("Service: {}", service);
        logger.info("\tRepo1: {}", r1);
        logger.info("\tRepo2: {}", r2);
        logger.info("\tsame: {}", r1 == r2);

    }

}
