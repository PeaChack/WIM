package by.peachack.wim.service.market;

import by.peachack.wim.model.market.Order;

import java.util.List;

public interface WMRestClient {
    List<Order> getItemOrders(String itemUrl);
}
