package by.peachack.wim.dto.inventory.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InventoryPostDTO {
    private int platinum_balance;
    private int ducats_balance;
    private List<InventoryItemPostDTO> inventory_items;
}
