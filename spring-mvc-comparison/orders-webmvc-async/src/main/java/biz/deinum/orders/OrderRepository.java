package biz.deinum.orders;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

interface OrderRepository extends MongoRepository<Order, Long> {

    Optional<Order> findByNumber(String number);

}
