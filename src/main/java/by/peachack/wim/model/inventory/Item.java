package by.peachack.wim.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "url_name", unique = true)
    private String urlName;
    @Column(name = "trading_tax")
    private int tradingTax;
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType type;

    public Item(String name, String urlName, int tradingTax, ItemType type) {
        this.name = name;
        this.urlName = urlName;
        this.tradingTax = tradingTax;
        this.type = type;
    }
    public Item(Item item){
        this.id = item.id;
        this.name = item.name;
        this.urlName = item.urlName;
        this.tradingTax = item.tradingTax;
        this.type = item.type;
    }
}
