package by.peachack.wim.model.market;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Order {
    private OrderType type;
    private int quantity;
    private int platinum;
    private PlayerStatus playerStatus;
}
