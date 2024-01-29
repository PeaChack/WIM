package by.peachack.wim.repositories;

import by.peachack.wim.model.UserItem;
import by.peachack.wim.model.UserItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItem, UserItemKey> {
}
