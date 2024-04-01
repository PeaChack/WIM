package by.peachack.wim.model.inventory;

import by.peachack.wim.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventories")
@Getter
public class Inventory {
    @Id
    @Column(name = "id")
    private UUID id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    private int platinumBalance;
    private int ducatsBalance;
    @OneToMany(mappedBy = "inventory")
    private List<InventoryItem> inventory;
}
