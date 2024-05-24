package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.get.InventoryItemGetDTO;
import by.peachack.wim.dto.inventory.post.InventoryItemPostDTO;
import by.peachack.wim.model.inventory.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryItemMapper {
    InventoryItemMapper INSTANCE = Mappers.getMapper(InventoryItemMapper.class);
    InventoryItemGetDTO inventoryItemToInventoryItemDTO(InventoryItem inventoryItem);
    InventoryItem inventoryItemDTOToInventoryItem(InventoryItemPostDTO inventoryItemPostDTO);
}
