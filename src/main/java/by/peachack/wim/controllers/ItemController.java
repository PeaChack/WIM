package by.peachack.wim.controllers;

import by.peachack.wim.dto.inventory.ItemDTO;
import by.peachack.wim.dto.mappers.ItemMapper;
import by.peachack.wim.services.inventory.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping
    public List<ItemDTO> getItems() {
        List<ItemDTO> list = itemService.getAllItems().stream().map(itemMapper::itemToItemDTO).toList();
        return list;
    }
}
