package by.peachack.wim.repository;

import by.peachack.wim.model.inventory.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByUrlName(String itemUrlName);
}
