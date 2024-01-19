package by.peachack.wim.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserItemKey implements Serializable {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "item_id")
    String itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserItemKey that = (UserItemKey) o;
        return userId.equals(that.userId) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, itemId);
    }
}
