package by.peachack.wim.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItem {
    @EmbeddedId
    private InventoryItemKey id;

    @ManyToOne
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "quantity")
    private Integer quantity;

    public InventoryItem(Inventory inventory, Item item, Integer quantity) {
        this.inventory = inventory;
        this.item = item;
        this.quantity = quantity;
        this.id = new InventoryItemKey(inventory.getId(), item.getId());
    }
}
