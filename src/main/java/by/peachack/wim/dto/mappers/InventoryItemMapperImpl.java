package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.ItemDTO;
import by.peachack.wim.dto.inventory.get.InventoryItemGetDTO;
import by.peachack.wim.dto.inventory.post.InventoryItemPostDTO;
import by.peachack.wim.model.inventory.InventoryItem;
import by.peachack.wim.model.inventory.Item;

public class InventoryItemMapperImpl implements InventoryItemMapper{
    private final ItemMapper itemMapper;

    public InventoryItemMapperImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public InventoryItemGetDTO inventoryItemToInventoryItemDTO(InventoryItem inventoryItem) {
        Item item = inventoryItem.getItem();
        ItemDTO itemDTO = itemMapper.itemToItemDTO(item);
        return new InventoryItemGetDTO(itemDTO, inventoryItem.getQuantity());
    }

    @Override
    public InventoryItem inventoryItemDTOToInventoryItem(InventoryItemPostDTO inventoryItemPostDTO) {

        return null;
    }
}
