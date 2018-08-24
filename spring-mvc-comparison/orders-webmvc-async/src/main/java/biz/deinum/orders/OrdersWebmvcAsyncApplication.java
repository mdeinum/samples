package biz.deinum.orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrdersWebmvcAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersWebmvcAsyncApplication.class, args);
    }

    @Bean
    public ApplicationRunner createOrders(OrderService orderService) {
        return args -> IntStream.range(0, 10).boxed()
                .map(i -> new Order(
                        BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(i, 1000)).setScale(2, RoundingMode.HALF_UP)))
                .map(orderService::newOrder)
                .forEach(System.out::println);
    }
}
