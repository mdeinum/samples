package biz.deinum.orders.web;

import java.net.URI;
import java.util.List;

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
    public List<Order> list() {
        return orderService.allOrders();
    }

    @GetMapping("/{number}")
    public ResponseEntity<Order> find(@PathVariable String number) {
        return orderService.findOrderWith(number)
                .map(order -> ResponseEntity.ok(order))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Order order, UriComponentsBuilder uriBuilder) {
        Order savedOrder = orderService.newOrder(order);
        return ResponseEntity.created(uriBuilder.path("/orders/{number}").build(savedOrder.getNumber())).build();
    }
}
