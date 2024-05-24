package by.peachack.wim.repositories;

import by.peachack.wim.model.inventory.InventoryItem;
import by.peachack.wim.model.inventory.InventoryItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, InventoryItemKey> {
    List<InventoryItem> findByInventory_User_Username(String username);

}
