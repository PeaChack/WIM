package by.peachack.wim.services.inventory;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.model.inventory.ItemType;
import by.peachack.wim.model.inventory.PrimePart;
import by.peachack.wim.util.json.JsonItemParser;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FileSystemJsonItemLoader implements ItemLoader {
    private final String PATH_TO_FOLDER_WITH_ITEM_JSONS;
    private final JsonItemParser itemParser;

    @Override
    public List<Item> findAllItems() {
        List<File> files = getFiles(PATH_TO_FOLDER_WITH_ITEM_JSONS);

        Map<File, Item> itemFileMap = files.stream().collect(Collectors.toMap(file -> file, this::fileToItem));

        List<Item> itemList = new ArrayList<>(itemFileMap.entrySet().stream()
                .map(itemFileEntry -> {
                    Item item = itemFileEntry.getValue();
                    ItemType type = item.getType();
                    if (type.equals(ItemType.PRIME_SET)) {
                        File setFile = itemFileEntry.getKey();
                        List<String> urls = itemParser.extractSetUrls(setFile);
                        List<PrimePart> partList = itemFileMap.entrySet().stream()
                                .filter(itemFileEntry1 -> urls.contains(itemFileEntry1.getValue().getUrlName()))
                                .map(itemFileEntry1 -> itemParser.parsePrimePart(itemFileEntry1.getValue(), itemFileEntry1.getKey()))
                                .toList();

                        itemFileEntry.setValue(itemParser.parsePrimeSet(item, partList, setFile));
                    }
                    return itemFileEntry.getValue();
                })
                .toList());
        itemList.removeIf(item -> item.getType().equals(ItemType.PRIME_PART));
        return itemList;
    }

    private Item fileToItem(File file) {
        try {
            return itemParser.parseItem(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse file + " + file.getPath());
        }
    }

    private List<File> getFiles(String path) {
        File directory = new File(path);
        File[] filesArray = directory.listFiles((dir, name) -> name.endsWith(".json"));
        List<File> files;
        if (filesArray == null)
            files = Collections.emptyList();
        else
            files = Arrays.asList(filesArray);
        return files;
    }

}
