package biz.deinum.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@SpringBootApplication
public class OrderJdbiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderJdbiApplication.class, args);
    }

    @Bean
    public ApplicationRunner ordering(OrderRepository orderRepo) {
        return args -> {

            List<Order> allOrders = orderRepo.findAll();
            System.out.println("# Orders: " + allOrders.size());
            System.out.println("Orders: " + allOrders);

            Order order1 = createRandomOrder();
            orderRepo.save(order1);

            allOrders = orderRepo.findAll();
            System.out.println("# Orders: " + allOrders.size());
            System.out.println("Orders: " + allOrders);


            List<Order> orders = new ArrayList<>(50);
            for (int i = 0; i < 50; i++) {
                orders.add(createRandomOrder());
            }
            orderRepo.saveAll(orders);
            allOrders = orderRepo.findAll();
            System.out.println("# Orders: " + allOrders.size());
            System.out.println("Orders: " + allOrders);

            Optional<Order> order2FromDb = orderRepo.findById(orders.get(ThreadLocalRandom.current().nextInt(50)).getId());
            System.out.println(order2FromDb);
            Optional<Order> noOrderFromDb = orderRepo.findById("noop");
            System.out.println(noOrderFromDb);
        };
    }

    private static Order createRandomOrder() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        String id = UUID.randomUUID().toString();
        return new Order(id, BigDecimal.valueOf(amount));
    }

    /**
     * Configure JDBI with the Spring Boot configured {@link DataSource} and
     * automatically registerig detect plugins. Additionally the {@link H2DatabasePlugin}
     * is added as that isn't automatically registered.
     *
     * @param dataSource
     * @return
     */
    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        // JDBI wants to control the Connection wrap the datasource in a proxy
        // That is aware of the Spring managed transaction
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        jdbi.installPlugins();
        jdbi.installPlugin(new H2DatabasePlugin());

        jdbi.registerRowMapper(Order.class, ConstructorMapper.of(Order.class));

        return jdbi;
    }
}

