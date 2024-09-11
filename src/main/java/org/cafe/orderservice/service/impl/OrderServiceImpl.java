package org.cafe.orderservice.service.impl;

import org.cafe.commons.entity.LineItemEntity;
import org.cafe.commons.entity.OrderEntity;
import org.cafe.commons.repo.OrderRepo;
import org.cafe.commons.shared.Status;
import org.cafe.orderservice.domain.LineItemRequest;
import org.cafe.orderservice.domain.OrderRequest;
import org.cafe.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderEntity save(OrderRequest orderRequest) {
        OrderEntity orderEntity = OrderRequest.toEntity(orderRequest.getLocation(), Status.PAYMENT_EXPECTED, orderRequest.getItems());
        return orderRepo.save(orderEntity);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepo.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderEntity update(UUID orderId, OrderRequest orderRequest) {
        OrderEntity existingOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        existingOrder.setLocation(orderRequest.getLocation());
        List<LineItemEntity> existingLineItems = existingOrder.getLineItems();
        List<LineItemRequest> updatedLineItems = orderRequest.getItems();

        existingLineItems.clear();
        updatedLineItems.forEach(
                lineItemRequest -> existingOrder.addLineItem(
                        LineItemRequest.toEntity(lineItemRequest)
                )
        );

        OrderEntity updatedOrder = orderRepo.save(existingOrder);

        return updatedOrder;
    }

    @Override
    public void delete(UUID orderId) {
        if (orderRepo.existsById(orderId)) {
            orderRepo.deleteById(orderId);
        }
    }

}