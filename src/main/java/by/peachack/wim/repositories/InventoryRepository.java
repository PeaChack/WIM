package by.peachack.wim.repositories;

import by.peachack.wim.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Inventory findByUser_Username(String username);
}
