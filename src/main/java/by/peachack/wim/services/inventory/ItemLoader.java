package by.peachack.wim.services.inventory;

import by.peachack.wim.model.inventory.Item;

import java.util.List;

public interface ItemLoader {
    List<Item> findAllItems();
}
