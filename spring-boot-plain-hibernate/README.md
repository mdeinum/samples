Spring Boot with Plain Hibernate
===

By default Spring Boot registers a EntityManagerFactory when the appropriate classes are detected (i.e. the hibernate entitymanager).

When migrating a sample from plain Hibernate to JPA (or when using both technologies) there is a nice class
[`HibernateJpaSessionFactoryBean`][1] that exposes the underlying `SessionFactory` for the `EntityManagerFactory` so that
it can be used. To enable this simply add it as a bean to your configuration.

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

When using this bean there is one thing that needs to be taken care of when setting up JPA Spring doesn't set the
`hibernate.current_session_context_class` which results in a stacktrace telling the there is not CurrentSessionContext
class set. This can be easy fixed by adding a property to the `application.properties` file used by Spring Boot.

    spring.jpa.properties.hibernate.current_session_context_class=org.springframework.orm.hibernate4.SpringSessionContext

Now it is possible to write a DAO which uses the `SessionFactory`.

    public class HibernateCompanyDao implements CompanyDao {

        private final SessionFactory sf;

        @Autowired
        public HibernateCompanyDao(SessionFactory sf) {
            this.sf=sf;
        }

    }


[1]: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/orm/jpa/vendor/HibernateJpaSessionFactoryBean.html