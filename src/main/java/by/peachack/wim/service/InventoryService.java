package by.peachack.wim.service;

import by.peachack.wim.model.Inventory;
import by.peachack.wim.model.UserItem;
import by.peachack.wim.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).orElseThrow();
    }
}
