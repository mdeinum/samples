package biz.deinum.orders;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

interface OrderRepository extends ReactiveMongoRepository<Order, Long> {

    Mono<Order> findByNumber(String number);

}
