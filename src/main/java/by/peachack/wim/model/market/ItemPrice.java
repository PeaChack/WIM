package by.peachack.wim.model.market;


import by.peachack.wim.model.inventory.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Entity class contains prices on warframe.market
 * @author PeaChack*/
@Getter
@Entity(name = "item_prices")
@RequiredArgsConstructor
public class ItemPrice {
    @Id
    private UUID id;
    @ManyToOne
    private Item item;
    private int buyerPrice;
    private int sellerPrice;
    @UpdateTimestamp
    private Timestamp updateTime;
}
