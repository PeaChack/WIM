package by.peachack.wim.service.market;

import by.peachack.wim.model.inventory.*;
import by.peachack.wim.model.market.OptimizerReport;
import by.peachack.wim.repository.InventoryItemRepository;
import by.peachack.wim.repository.InventoryRepository;
import by.peachack.wim.repository.ItemPriceRepository;
import by.peachack.wim.service.Optimizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DealService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryRepository inventoryRepository;

    private final ItemPriceRepository priceRepository;
    private final Optimizer optimizer;


    public OptimizerReport findDeals(String username, int tradesCount) {

        Inventory userInventory = inventoryRepository.findByUser_Username(username);
        List<InventoryItem> userItems = userInventory.getItems();
        Set<PrimeSet> expandedPrimeSets = new HashSet<>();
        List<InventoryItem> userPrimeParts = userItems.stream().filter(inventoryItem -> inventoryItem
                        .getItem()
                        .getType()
                        .equals(ItemType.PRIME_PART))
                .toList();
        userPrimeParts.stream().map(inventoryItem -> {
                    PrimePart primePart = (PrimePart) inventoryItem.getItem();
                    return primePart.getSetItem();
                })
                .forEach(expandedPrimeSets::add);
        int platinumBalance = userInventory.getPlatinumBalance();
        return optimizer.optimize(expandedPrimeSets, userItems, tradesCount, platinumBalance);
    }
}
