package by.peachack.wim.dto.inventory.get;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class InventoryGetDTO {
    private int platinum_balance;
    private int ducats_balance;
    private List<InventoryItemGetDTO> items;
}
