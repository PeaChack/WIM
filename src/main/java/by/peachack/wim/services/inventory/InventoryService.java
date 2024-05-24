package by.peachack.wim.services.inventory;

import by.peachack.wim.dto.inventory.post.InventoryItemPostDTO;
import by.peachack.wim.dto.inventory.post.InventoryPostDTO;
import by.peachack.wim.model.inventory.Inventory;
import by.peachack.wim.model.inventory.InventoryItem;
import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.repositories.InventoryItemRepository;
import by.peachack.wim.repositories.InventoryRepository;
import by.peachack.wim.repositories.ItemRepository;
import by.peachack.wim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    private final ItemRepository itemRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository, InventoryItemRepository inventoryItemRepository, UserRepository userRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.userRepository = userRepository;
    }

    public Inventory getInventoryById(UUID id) {
        return inventoryRepository.findById(id).orElseThrow();
    }

    public Inventory getInventoryByUsername(String username) {
        UserDetails userDetails = userRepository.findByUsername(username).orElseThrow();
        return inventoryRepository.findByUser_Username(username);
    }

    public void saveInventory(Inventory inventory) {
        inventoryItemRepository.saveAll(inventory.getItems());
    }

    public void saveInventory(InventoryPostDTO inventoryDTO, String ownerUsername) {
        Inventory inventory = inventoryRepository.findByUser_Username(ownerUsername);
        List<InventoryItem> items = inventory.getItems();
        List<InventoryItemPostDTO> dtoItems = inventoryDTO.getInventory_items();

        dtoItems.forEach(inventoryItemPostDTO -> {
            String itemUrlName = inventoryItemPostDTO.getUrl_name();
            int quantity = inventoryItemPostDTO.getQuantity();

            Optional<Item> itemOptional = itemRepository.findByUrlName(itemUrlName);
            itemOptional.ifPresentOrElse(item -> {
                items.add(new InventoryItem(inventory, item, quantity));
            }, () -> {
                throw new IllegalArgumentException(String.format("Item %s not found", itemUrlName));
            });

        });

        inventoryRepository.save(inventory);
    }
}
