package biz.deinum.orders;

import java.util.List;
import java.util.Optional;

interface OrderRepository {

    List<Order> findAll();
    Optional<Order> findById(String id);

    Order save(Order order);
    List<Order> saveAll(List<Order> orders);
}
