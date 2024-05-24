package by.peachack.wim.dto.inventory;

import by.peachack.wim.model.inventory.ItemType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              include = JsonTypeInfo.As.EXISTING_PROPERTY,
              property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrimePartDTO.class, name = "PRIME_PART"),
        @JsonSubTypes.Type(value = PrimeSetDTO.class, name = "PRIME_SET")
})
public class ItemDTO {
    private String name;
    private String urlName;
    private int tradingTax;
    private ItemType type;
    private List<String> tags;
}
