package by.peachack.wim.services.inventory;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    public void saveAllItems(List<Item> items){
        itemRepository.saveAll(items);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
