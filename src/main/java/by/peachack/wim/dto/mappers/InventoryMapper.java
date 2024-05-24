package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.get.InventoryGetDTO;
import by.peachack.wim.dto.inventory.post.InventoryPostDTO;
import by.peachack.wim.model.inventory.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);
    InventoryGetDTO inventoryToInventoryDTO(Inventory inventory);
    Inventory inventoryDTOToInventory(InventoryPostDTO inventoryDTO);
}
