package by.peachack.wim.services.market;

import by.peachack.wim.model.market.Order;

import java.util.List;

public interface WMRestClient {
    List<Order> getItemOrders(String itemUrl);
}
