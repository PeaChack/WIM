package by.peachack.wim.model.inventory;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "prime_sets")
public class PrimeSet extends Item {
    @OneToMany(mappedBy = "setItem")
    private List<PrimePart> primePartList;
    @Transient
    private int ducatsPrice;
    @Transient
    private int masteryLevel;
}
