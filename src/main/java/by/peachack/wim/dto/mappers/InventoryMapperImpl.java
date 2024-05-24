package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.get.InventoryGetDTO;
import by.peachack.wim.dto.inventory.get.InventoryItemGetDTO;
import by.peachack.wim.dto.inventory.post.InventoryPostDTO;
import by.peachack.wim.model.inventory.Inventory;

import java.util.List;

public class InventoryMapperImpl implements InventoryMapper {
    private final InventoryItemMapper inventoryItemMapper;

    public InventoryMapperImpl(InventoryItemMapper inventoryItemMapper) {
        this.inventoryItemMapper = inventoryItemMapper;
    }

    @Override
    public InventoryGetDTO inventoryToInventoryDTO(Inventory inventory) {
        int platinumBalance = inventory.getPlatinumBalance();
        int ducatsBalance = inventory.getDucatsBalance();
        List<InventoryItemGetDTO> itemsDTO = inventory.getItems().stream()
                .map(inventoryItemMapper::inventoryItemToInventoryItemDTO)
                .toList();
        return new InventoryGetDTO(platinumBalance, ducatsBalance, itemsDTO);
    }

    @Override
    public Inventory inventoryDTOToInventory(InventoryPostDTO inventoryDTO) {
        return null;
    }
}
