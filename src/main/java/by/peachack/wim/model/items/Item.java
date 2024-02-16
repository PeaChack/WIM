package by.peachack.wim.model.items;

import by.peachack.wim.model.enums.ItemType;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
public class Item {
    @Id
    private String Id;
    @Column(name = "name")
    private String name;
    @Column(name = "url_name")
    private String urlName;
    @Column(name = "trading_tax")
    private int tradingTax;
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType type;

}
