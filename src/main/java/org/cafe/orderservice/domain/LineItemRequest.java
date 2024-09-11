package org.cafe.orderservice.domain;

import org.cafe.commons.entity.LineItemEntity;
import org.cafe.commons.shared.Drink;
import org.cafe.commons.shared.Milk;
import org.cafe.commons.shared.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineItemRequest {
    @NotNull(message = "Drink must not be empty.")
    private Drink drink;
    @NotNull(message = "Size must not be empty.")
    private Size size;
    @NotNull(message = "Milk must not be empty.")
    private Milk milk;
    @Min(value = 1, message = "Quantity must not be zero.")
    private int quantity;

    public static LineItemEntity toEntity(LineItemRequest lineItemRequest) {
       return LineItemEntity.builder()
                            .drink(lineItemRequest.getDrink())
                            .size(lineItemRequest.getSize())
                            .milk(lineItemRequest.getMilk())
                            .quantity(lineItemRequest.getQuantity())
                            .build();
    }

}
