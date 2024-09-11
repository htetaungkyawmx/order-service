package org.cafe.orderservice.service;

import org.cafe.commons.entity.OrderEntity;
import org.cafe.orderservice.domain.OrderRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderEntity save(OrderRequest orderRequest);

    List<OrderEntity> findAll();

    OrderEntity update(UUID orderId, OrderRequest orderRequest);

    void delete(UUID orderId);

}
