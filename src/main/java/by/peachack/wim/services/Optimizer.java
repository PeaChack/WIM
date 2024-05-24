package by.peachack.wim.services;

import by.peachack.wim.model.inventory.*;
import by.peachack.wim.model.market.ItemPrice;
import by.peachack.wim.model.market.OptimizerReport;
import by.peachack.wim.services.market.ItemPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class Optimizer {
    private final ItemPriceService itemPriceService;

    public OptimizerReport optimize(Set<PrimeSet> expandedPrimeSets,
                                    List<InventoryItem> userInventory,
                                    int availableTradesCount,
                                    int platinumBalance) {
        Map<Item, ItemPrice> prices = new HashMap<>();
        expandedPrimeSets.forEach(primeSet -> {
            ItemPrice setPrice = itemPriceService.getItemPrice(primeSet.getUrlName());
            prices.put(primeSet, setPrice);
            primeSet.getPrimePartList().forEach(primePart -> {
                ItemPrice partPrice = itemPriceService.getItemPrice(primePart.getUrlName());
                prices.put(primePart, partPrice);
            });
        });

        Map<PrimeSet, Float> greedyMetrics = new HashMap<>();
        Map<PrimeSet, Float> mediumMetrics = new HashMap<>();
        Map<PrimeSet, Float> generousMetrics = new HashMap<>();
        LinkedList
        expandedPrimeSets.forEach(primeSet -> {
            int setCompositeSellerPrice = prices.get(primeSet).getSellerPrice();
            int setCompositeBuyerPrice = prices.get(primeSet).getSellerPrice();
            int setDividedBuyerPrice = primeSet.getPrimePartList().stream()
                    .mapToInt(value -> prices.get(value).getBuyerPrice())
                    .sum();
            int setDividedSellerPrice = primeSet.getPrimePartList().stream()
                    .mapToInt(value -> prices.get(value).getSellerPrice())
                    .sum();
            int totalTradesCount = 1 + primeSet.getPrimePartList().size();
            float greedyMetric = (float) (setCompositeSellerPrice - setDividedBuyerPrice) ;
            float generousMetric = (float) (setCompositeBuyerPrice - setDividedSellerPrice) ;
            greedyMetrics.put(primeSet, greedyMetric);
            generousMetrics.put(primeSet, generousMetric);

            int inventoryCosts = primeSet.getPrimePartList().stream()
                    .mapToInt(primePart -> {
                        ItemPrice itemPrice = prices.get(primePart);
                        int needToBuy;
                        Optional<InventoryItem> item = userInventory.stream()
                                .filter(inventoryItem -> inventoryItem.getItem().equals(primePart))
                                .findFirst();
                        int quantityForSet = primePart.getQuantityForSet();
                        if (item.isEmpty())
                            needToBuy = quantityForSet;
                        else {
                            Integer quantityInInventory = item.get().getQuantity();
                            needToBuy = quantityInInventory < quantityForSet ? quantityForSet - quantityInInventory : 0;
                        }

                        int spread = itemPrice.getSellerPrice() - itemPrice.getBuyerPrice();
                        return spread * needToBuy;
                    })
                    .sum();
            float mediumMetric = setCompositeBuyerPrice - setDividedBuyerPrice - inventoryCosts;
            mediumMetrics.put(primeSet, mediumMetric);
        });

        return new OptimizerReport(greedyMetrics, mediumMetrics, generousMetrics);
    }
}
