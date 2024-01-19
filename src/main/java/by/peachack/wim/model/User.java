package by.peachack.wim.model;

import by.peachack.wim.model.enums.RoleEnum;
import by.peachack.wim.model.items.Item;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private RoleEnum role;

    @OneToMany
    private List<Item> inventory;

}
