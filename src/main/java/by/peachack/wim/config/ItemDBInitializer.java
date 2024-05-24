package by.peachack.wim.config;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.services.inventory.ItemLoader;
import by.peachack.wim.services.inventory.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemDBInitializer implements CommandLineRunner {
    private final ItemService itemService;
    private final ItemLoader itemLoader;
    @Override
    public void run(String... args) throws Exception {
        List<Item> items = itemService.getAllItems();
        if (items.isEmpty()) {
            items = itemLoader.findAllItems();
            itemService.saveAllItems(items);
        }
    }
}
    