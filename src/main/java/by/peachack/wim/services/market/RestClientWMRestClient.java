package by.peachack.wim.services.market;

import by.peachack.wim.model.market.Order;
import by.peachack.wim.model.market.OrderType;
import by.peachack.wim.model.market.PlayerStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
//TODO Rework, extract some code to JsonItemPriceParser, divide code by small parts
@RequiredArgsConstructor
public class RestClientWMRestClient implements WMRestClient {
    private final RestClient restClient;


    @Override
    public List<Order> getItemOrders(String itemUrl) {
        String response = restClient.get()
                .uri(String.format("/items/%s/orders", itemUrl))
                .retrieve()
                .body(String.class);
        List<Order> orders = parseOrders(response);
        return orders;

    }
    private static List<Order> parseOrders(String response) {
        List<Order> orders = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (jsonNode != null) {
            JsonNode ordersNode = jsonNode.path("payload").path("orders");
            for (JsonNode orderNode : ordersNode) {
                String ingameName = orderNode.path("user").path("ingame_name").asText();
                String orderTypeStr = orderNode.path("order_type").asText();
                OrderType orderType = OrderType.valueOf(orderTypeStr.toUpperCase());
                int quantity = orderNode.path("quantity").asInt();
                int platinum = orderNode.path("platinum").asInt();
                String playerStatusStr = orderNode.path("user").path("status").asText();
                PlayerStatus playerStatus = PlayerStatus.valueOf(playerStatusStr.toUpperCase());
                Order order = new Order(orderType, quantity, platinum, playerStatus);
                orders.add(order);
            }
        }
        return orders;
    }
}
