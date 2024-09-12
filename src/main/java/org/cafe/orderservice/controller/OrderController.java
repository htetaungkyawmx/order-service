package org.cafe.orderservice.controller;

import org.cafe.commons.entity.OrderEntity;
import org.cafe.orderservice.client.PaymentClient;
import org.cafe.orderservice.domain.OrderRequest;
import org.cafe.orderservice.domain.PaymentRequest;
import org.cafe.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentClient paymentClient;

    @PostMapping("/create-order")
    public ResponseEntity<OrderEntity> createOrder(@Validated @RequestBody OrderRequest orderRequest, BindingResult result) {
        OrderEntity orderEntity = orderService.save(orderRequest);
        paymentClient.pay(new PaymentRequest());
        return ResponseEntity.ok(orderEntity);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable UUID orderId, @Validated @RequestBody OrderRequest orderRequest, BindingResult result) {
        OrderEntity orderEntity = orderService.update(orderId, orderRequest);
        return ResponseEntity.ok(orderEntity);
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") UUID id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}