package by.peachack.wim.dto.inventory.get;

import by.peachack.wim.dto.inventory.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryItemGetDTO {
    private ItemDTO item;
    private int quantity;
}
