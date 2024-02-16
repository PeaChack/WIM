package by.peachack.wim.model;

import by.peachack.wim.model.items.Item;
import jakarta.persistence.*;

@Entity
@Table(name = "user_items")
public class UserItem {
    @EmbeddedId
    UserItemKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "quantity")
    private Integer quantity;
}
