package biz.deinum.sample.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import java.util.List;

/**
 * Created by marten on 11-01-15.
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class);

        CompanyDao dao = ctx.getBean(CompanyDao.class);

        Company c = new Company();
        c.setName("Conspect Consulting & ICT");

        dao.add(c);

        List<Company> companies = dao.findAll();
        logger.info("Companies: {}", companies);

    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

}
