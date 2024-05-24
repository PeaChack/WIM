package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.ItemDTO;
import by.peachack.wim.model.inventory.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    @Mapping(target = "id", ignore = true)
    ItemDTO itemToItemDTO(Item item);
    Item itemDTOToItem(ItemDTO itemDTO);
}
