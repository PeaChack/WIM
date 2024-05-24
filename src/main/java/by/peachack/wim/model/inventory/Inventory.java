package by.peachack.wim.model.inventory;

import by.peachack.wim.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "inventories")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @Column(name = "platinum_balance")
    private int platinumBalance = 0;
    @Column(name = "ducats_balance")
    private int ducatsBalance = 0;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<InventoryItem> items;

    public Inventory(User owner, int platinum, int ducats) {
        this.user = owner;
        this.platinumBalance = platinum;
        this.ducatsBalance = ducats;
        this.items = new ArrayList<>();
    }
}
