package by.peachack.wim.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Inventory {
    @Id
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "user")
    private List<UserItem> inventory;
}
