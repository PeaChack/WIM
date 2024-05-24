package by.peachack.wim.controllers;

import by.peachack.wim.dto.inventory.get.InventoryGetDTO;
import by.peachack.wim.dto.inventory.post.InventoryPostDTO;
import by.peachack.wim.dto.mappers.InventoryMapper;
import by.peachack.wim.dto.mappers.ItemMapper;
import by.peachack.wim.model.inventory.Inventory;
import by.peachack.wim.services.inventory.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryMapper inventoryMapper;

    public InventoryController(InventoryService inventoryService, InventoryMapper inventoryMapper) {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    @PostMapping()
    public void saveItems(Authentication authentication, @RequestBody InventoryPostDTO inventoryDTO) {
        inventoryService.saveInventory(inventoryDTO, authentication.getName());
    }
    @GetMapping
    public InventoryGetDTO getItems(Authentication authentication){
        String username = authentication.getName();
        Inventory inventory = inventoryService.getInventoryByUsername(username);
        return inventoryMapper.inventoryToInventoryDTO(inventory);
    }

    @GetMapping("/{username}")
    public InventoryGetDTO getItems(@PathVariable String username){
        Inventory inventory = inventoryService.getInventoryByUsername(username);
        return inventoryMapper.inventoryToInventoryDTO(inventory);
    }

}
