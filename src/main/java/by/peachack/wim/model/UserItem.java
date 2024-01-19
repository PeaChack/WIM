package by.peachack.wim.model;

import by.peachack.wim.model.items.Item;
import jakarta.persistence.*;

@Entity
@Table(name = "user_items")
public class UserItem {
    @EmbeddedId
    UserItemKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "quantity")
    private Integer quantity;
}
