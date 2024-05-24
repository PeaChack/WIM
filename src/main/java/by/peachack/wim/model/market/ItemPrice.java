package by.peachack.wim.model.market;


import by.peachack.wim.model.inventory.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * Entity class contains prices on warframe.market
 * @author PeaChack*/
@Data
@Entity(name = "item_prices")
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Item item;
    @Column(name = "buyer_price")
    private int buyerPrice;
    @Column(name = "seller_price")
    private int sellerPrice;
    @UpdateTimestamp
    private Timestamp updateTime;

    public ItemPrice(Item item, int buyerPrice, int sellerPrice) {
        this.item = item;
        this.buyerPrice = buyerPrice;
        this.sellerPrice = sellerPrice;
        this.updateTime = Timestamp.from(Instant.now());
    }
}
