package org.cafe.orderservice.domain;

import org.cafe.commons.entity.OrderEntity;
import org.cafe.commons.shared.Location;
import org.cafe.commons.shared.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "Location must not be empty.")
    private Location location;
    private static Status status = Status.PAYMENT_EXPECTED;
    private List<@Valid LineItemRequest> items;

    public static OrderEntity toEntity(Location location, Status status, List<LineItemRequest> lineItemRequests) {
        OrderEntity order = new OrderEntity(location, status, LocalDateTime.now());
        lineItemRequests.forEach(
                lineItemRequest -> order.addLineItem(
                        LineItemRequest.toEntity(lineItemRequest)
                )
        );
        return order;
    }
}
