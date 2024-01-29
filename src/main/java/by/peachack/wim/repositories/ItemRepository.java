package by.peachack.wim.repositories;

import by.peachack.wim.model.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}
