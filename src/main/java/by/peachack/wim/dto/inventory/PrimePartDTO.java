package by.peachack.wim.dto.inventory;

import by.peachack.wim.model.inventory.ItemType;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonTypeName("PRIME_PART")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PrimePartDTO extends ItemDTO {
    private String set_url_name;
    private int quantity_for_set;

    public PrimePartDTO(String name, String urlName, int tradingTax, ItemType type, List<String> tags, String set_url_name, int quantity_for_set) {
        super(name, urlName, tradingTax, type, tags);
        this.set_url_name = set_url_name;
        this.quantity_for_set = quantity_for_set;
    }
}
