package biz.deinum.orders.web;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Future<List<Order>> list() {
        return CompletableFuture.supplyAsync(orderService::allOrders);
    }

    @GetMapping("/{number}")
    public Future<ResponseEntity<Order>> find(@PathVariable String number) {
        return CompletableFuture.supplyAsync(
                () -> orderService.findOrderWith(number).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Future<ResponseEntity<Void>> create(@RequestBody Order order, UriComponentsBuilder uriBuilder) {
        return CompletableFuture.supplyAsync(() -> {
            Order savedOrder = orderService.newOrder(order);
            return ResponseEntity.created(uriBuilder.path("/orders/{number}").build(savedOrder.getNumber())).build();
        });
    }
}
