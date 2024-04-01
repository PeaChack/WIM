package by.peachack.wim.model.inventory;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
@Getter
public class Item {
    @Id
    @Column(name = "id")
    private UUID Id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "url_name", unique = true)
    private String urlName;
    @Column(name = "trading_tax")
    private int tradingTax;
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType type;

}
