package by.peachack.wim.repositories;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.model.market.ItemPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemPriceRepository extends JpaRepository<ItemPrice, UUID> {
    Optional<ItemPrice> findByItem_UrlName(String itemUrlName);
}
