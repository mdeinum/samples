package biz.deinum.orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class OrdersWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersWebFluxApplication.class, args);
    }

    @Bean
    public ApplicationRunner createOrders(OrderService orderService) {
        return args -> Flux.fromStream(IntStream.range(0, 10).boxed())
                .map(i -> new Order(
                        BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(i, 1000)).setScale(2, RoundingMode.HALF_UP)))
                .map(orderService::newOrder)
                .doOnNext(m -> m.subscribe(System.out::println)).subscribe();
    }
}
