package by.peachack.wim.model.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItemKey implements Serializable {
    @Column(name = "inventory_id")
    UUID inventoryId;
    @Column(name = "item_id")
    UUID itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemKey that = (InventoryItemKey) o;
        return inventoryId.equals(that.inventoryId) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, itemId);
    }
}
