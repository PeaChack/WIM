package by.peachack.wim.services.market;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.model.market.ItemPrice;
import by.peachack.wim.model.market.Order;
import by.peachack.wim.model.market.OrderType;
import by.peachack.wim.model.market.PlayerStatus;
import by.peachack.wim.repositories.ItemPriceRepository;
import by.peachack.wim.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemPriceService {
    private final ItemPriceRepository itemPriceRepository;
    private final ItemRepository itemRepository;
    private final WMRestClient wmRestClient;

    public ItemPrice getItemPrice(String itemUrl){
        List<Order> orders = wmRestClient.getItemOrders(itemUrl);
        Optional<Order> maxBuyOrder = orders.stream()
                .filter(order -> order.getType().equals(OrderType.BUY))
                .filter(order -> order.getPlayerStatus().equals(PlayerStatus.INGAME))
                .max(Comparator.comparingInt(Order::getPlatinum));
        if (maxBuyOrder.isEmpty()) {
            maxBuyOrder = orders.stream()
                    .filter(order -> order.getType().equals(OrderType.BUY))
                    .filter(order -> order.getPlayerStatus().equals(PlayerStatus.ONLINE))
                    .max(Comparator.comparingInt(Order::getPlatinum));
            if (maxBuyOrder.isEmpty()) {
                maxBuyOrder = orders.stream()
                        .filter(order -> order.getType().equals(OrderType.BUY))
                        .max(Comparator.comparingInt(Order::getPlatinum));
                if (maxBuyOrder.isEmpty())
                    throw new IllegalArgumentException(String.format("not found orders for %s", itemUrl));
            }
        }

        Optional<Order> minSellOrder = orders.stream()
                .filter(order -> order.getType().equals(OrderType.SELL))
                .filter(order -> order.getPlayerStatus().equals(PlayerStatus.INGAME))
                .min(Comparator.comparingInt(Order::getPlatinum));
        if (minSellOrder.isEmpty()) {
            minSellOrder = orders.stream()
                    .filter(order -> order.getType().equals(OrderType.SELL))
                    .filter(order -> order.getPlayerStatus().equals(PlayerStatus.ONLINE))
                    .min(Comparator.comparingInt(Order::getPlatinum));
            if (minSellOrder.isEmpty()) {
                minSellOrder = orders.stream()
                        .filter(order -> order.getType().equals(OrderType.SELL))
                        .min(Comparator.comparingInt(Order::getPlatinum));
                if (minSellOrder.isEmpty())
                    throw new IllegalArgumentException(String.format("not found orders for %s", itemUrl));
            }
        }

        Optional<ItemPrice> itemPriceOptional = itemPriceRepository.findByItem_UrlName(itemUrl);
        int buyerPrice = maxBuyOrder.get().getPlatinum();
        int sellerPrice = minSellOrder.get().getPlatinum();
        ItemPrice itemPrice;
        if (itemPriceOptional.isPresent()) {
            itemPrice = itemPriceOptional.get();
            itemPrice.setBuyerPrice(buyerPrice);
            itemPrice.setSellerPrice(sellerPrice);
            itemPriceRepository.save(itemPrice);
        } else {
            Item item = itemRepository.findByUrlName(itemUrl).orElseThrow(IllegalArgumentException::new);
            itemPrice = new ItemPrice(item, buyerPrice, sellerPrice);
            itemPriceRepository.save(itemPrice);
        }
        return itemPrice;
    }
}
