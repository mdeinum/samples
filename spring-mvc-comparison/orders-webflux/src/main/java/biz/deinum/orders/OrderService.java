package biz.deinum.orders;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final AtomicLong numberGenerator = new AtomicLong(10000);

    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Mono<Order> newOrder(Order order) {
        if (order.getNumber() == null) {
            order.setNumber(String.valueOf(numberGenerator.incrementAndGet()));
        }
        return orderRepository.save(order);
    }

    public Mono<Order> findOrderWith(String number) {
        return orderRepository.findByNumber(number);
    }

    public Flux<Order> allOrders() {
        return orderRepository.findAll();
    }
}
