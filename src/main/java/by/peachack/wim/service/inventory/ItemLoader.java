package by.peachack.wim.service.inventory;

import by.peachack.wim.model.inventory.Item;

import java.util.List;

public interface ItemLoader {
    List<Item> findAllItems();
}
