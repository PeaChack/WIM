package by.peachack.wim.util.json;

import by.peachack.wim.model.inventory.Item;
import by.peachack.wim.model.inventory.ItemType;
import by.peachack.wim.model.inventory.PrimePart;
import by.peachack.wim.model.inventory.PrimeSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonItemParser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectReader objectReader = objectMapper.readerFor(new TypeReference<List<String>>() {
    });

    public Item parseItem(File file) throws IOException {

        JsonNode jsonNode = objectMapper.readTree(file);
        String urlName = jsonNode.get("url_name").asText();
        int tradingTax = jsonNode.get("trading_tax").asInt();
        String itemName = jsonNode.get("i18n")
                .get("en")
                .get("item_name").asText();
        List<String> tags = objectReader.readValue(jsonNode.get("tags"));
        ItemType itemType = getItemType(tags, jsonNode);
        return new Item(itemName, urlName, tradingTax, itemType, tags);
    }

    public PrimePart parsePrimePart(Item item, File file) {
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            int quantityForSet = 0;
            try {
                quantityForSet = jsonNode.get("quantity_for_set").asInt();
                int masteryLevel = 0;
                if (jsonNode.has("mastery_level"))
                    masteryLevel = jsonNode.get("mastery_level").asInt();
                int ducats = 0;
                if (jsonNode.has("ducats"))
                    ducats = jsonNode.get("ducats").asInt();
                return new PrimePart(item, quantityForSet, masteryLevel, ducats);
            } catch (Exception e) {
                System.out.println(item.getName());
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PrimeSet parsePrimeSet(Item itemSet, List<PrimePart> parts, File file) {
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            int masteryLevel = 0;
            if (jsonNode.has("mastery_level"))
                masteryLevel = jsonNode.get("mastery_level").asInt();
            int ducats = 0;
            if (jsonNode.has(ducats))
                ducats = jsonNode.get("ducats").asInt();
            PrimeSet set = new PrimeSet(itemSet, parts, ducats, masteryLevel);
            for (PrimePart primePart : parts)
                primePart.setSetItem(set);
            return set;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(file.getName());
            return null;
        }
    }

    public List<String> extractSetUrls(File setFile) {
        try {
            JsonNode jsonNode = objectMapper.readTree(setFile);
            List<String> parts = objectReader.readValue(jsonNode.get("part_of_set"));
            parts.removeIf(s -> s.endsWith("set"));
            return parts;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private static ItemType getItemType(List<String> tags, JsonNode jsonNode) {
        if (tags.contains("set"))
            return ItemType.PRIME_SET;
        else if (tags.contains("component") || tags.contains("blueprint") || jsonNode.has("part_of_set"))
            return ItemType.PRIME_PART;
        else if (tags.contains("mod"))
            return ItemType.MOD;
        else if (tags.contains("relic"))
            return ItemType.RELIC;
        else if (tags.contains("ayatan_sculpture"))
            return ItemType.SCULPTURE;
        else if (tags.contains("ayatan_cyan_star") || tags.contains("ayatan_amber_star"))
            return ItemType.STAR;
        else if (tags.contains("arcane_enhancement"))
            return ItemType.ARCANE;
        else if (tags.contains("imprint"))
            return ItemType.IMPRINT;
        else if (tags.contains("fish"))
            return ItemType.FISH;
        else if (tags.contains("scene"))
            return ItemType.SCENE;
        else if (tags.contains("arcane_helmet"))
            return ItemType.HELMET;
        else if (tags.contains("syndicate") || tags.contains("weapon"))
            return ItemType.MERCHANT_ITEMS;
        else
            return ItemType.UNKNOWN;
    }
}
