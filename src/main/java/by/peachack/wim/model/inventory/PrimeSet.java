package by.peachack.wim.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "prime_sets")
@NoArgsConstructor
@AllArgsConstructor
public class PrimeSet extends Item {
    @OneToMany(mappedBy = "setItem", cascade = CascadeType.ALL)
    private List<PrimePart> primePartList;
    @Transient
    private transient int ducatsPrice;
    @Transient
    private transient int masteryLevel;

    public PrimeSet(Item item, List<PrimePart> primePartList, int ducatsPrice, int masteryLevel) {
        super(item);
        this.primePartList = primePartList;
        this.ducatsPrice = ducatsPrice;
        this.masteryLevel = masteryLevel;
    }
}
