package by.peachack.wim.dto.inventory.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryItemPostDTO {
    private String url_name;
    private int quantity;
}
