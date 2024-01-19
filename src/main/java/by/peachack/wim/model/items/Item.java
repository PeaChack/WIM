package by.peachack.wim.model.items;

import by.peachack.wim.model.enums.ItemType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public class Item {
    @Id
    private String Id;
    @Column(name = "name")
    private String name;
    @Column(name = "url_name")
    private String urlName;
    @Column(name = "trading_tax")
    private int tradingTax;
    @Column(name = "item_type")
    private ItemType type;

}
