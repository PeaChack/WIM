package by.peachack.wim.model;

import by.peachack.wim.model.enums.RoleEnum;
import by.peachack.wim.model.items.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.support.ManagedArray;

import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;
    @Column(name = "username")
    private String username;
    @Getter(AccessLevel.NONE)
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<UserItem> inventory;

}
