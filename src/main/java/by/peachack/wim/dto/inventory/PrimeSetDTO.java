package by.peachack.wim.dto.inventory;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("PRIME_SET")
public class PrimeSetDTO {
    private List<String> parts_urls;
}
