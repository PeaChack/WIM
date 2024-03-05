package by.peachack.wim.model;

import by.peachack.wim.model.enums.RoleEnum;
import by.peachack.wim.model.items.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Getter(AccessLevel.NONE)
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
