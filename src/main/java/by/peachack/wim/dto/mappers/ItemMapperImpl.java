package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.inventory.ItemDTO;
import by.peachack.wim.dto.inventory.PrimePartDTO;
import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.model.inventory.PrimePart;

public class ItemMapperImpl implements ItemMapper {
    @Override
    public ItemDTO itemToItemDTO(Item item) {
        if (item.getClass() == PrimePart.class) {
            PrimePart newItem = (PrimePart) item;
            return new PrimePartDTO(newItem.getName(),
                    newItem.getUrlName(),
                    newItem.getTradingTax(),
                    newItem.getType(),
                    newItem.getTags(),
                    newItem.getSetItem().getUrlName(),
                    newItem.getQuantityForSet());
        } else {
            return new ItemDTO(item.getName(),
                    item.getUrlName(),
                    item.getTradingTax(),
                    item.getType(),
                    item.getTags());
        }
    }

    @Override
    public Item itemDTOToItem(ItemDTO itemDTO) {
        return new Item(itemDTO.getName(),
                itemDTO.getUrlName(),
                itemDTO.getTradingTax(),
                itemDTO.getType(),
                itemDTO.getTags());
    }
}
