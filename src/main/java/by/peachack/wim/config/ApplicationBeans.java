package by.peachack.wim.config;

import by.peachack.wim.dto.mappers.*;
import by.peachack.wim.services.inventory.FileSystemJsonItemLoader;
import by.peachack.wim.services.market.RestClientWMRestClient;
import by.peachack.wim.services.market.WMRestClient;
import by.peachack.wim.util.json.JsonItemParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationBeans {

    @Bean
    public FileSystemJsonItemLoader itemLoader(@Value("${app.path-to-item-jsons}") String pathToJsons) {
        return new FileSystemJsonItemLoader(pathToJsons, new JsonItemParser());
    }

    @Bean
    public ItemMapper itemMapper() {
        return new ItemMapperImpl();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public InventoryItemMapper inventoryItemMapper() {
        return new InventoryItemMapperImpl(itemMapper());
    }

    @Bean
    public InventoryMapper inventoryMapper() {
        return new InventoryMapperImpl(inventoryItemMapper());
    }

    @Bean
    public WMRestClient wmRestClient(@Value("${app.warframe-market-base-uri}") String wMarketBaseUrl){
        return new RestClientWMRestClient(RestClient.builder()
                .baseUrl(wMarketBaseUrl)
                .build());
    }
}
