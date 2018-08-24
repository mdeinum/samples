package biz.deinum.orders;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final AtomicLong numberGenerator = new AtomicLong(10000);

    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order newOrder(Order order) {
        if (order.getNumber() == null) {
            order.setNumber(String.valueOf(numberGenerator.incrementAndGet()));
        }
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderWith(String number) {
        return orderRepository.findByNumber(number);
    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }
}
