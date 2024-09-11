package org.cafe.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cafe.commons.entity.OrderEntity;
import org.cafe.commons.shared.Status;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Status status;
    private BigDecimal amount;
    private String orderId;
}
