package biz.deinum.orders.web;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import biz.deinum.orders.Order;
import biz.deinum.orders.OrderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces =  { MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Flux<Order> list() {
        return orderService.allOrders();
    }

    @GetMapping("/{number}")
    public Mono<ResponseEntity<Order>> find(@PathVariable String number) {
        return orderService.findOrderWith(number)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> create(@RequestBody Order order, UriComponentsBuilder uriBuilder) {
        return orderService.newOrder(order)
                .map(savedOrder -> ResponseEntity.created(uriBuilder.path("/orders/{number}").build(savedOrder.getNumber())).build());
    }
}
