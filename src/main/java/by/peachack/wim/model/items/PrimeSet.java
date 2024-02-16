package by.peachack.wim.model.items;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PrimeSet extends Item {
    @OneToMany(mappedBy = "setItem")
    private List<PrimePart> primePartList;
    @Transient
    private int ducatsPrice;
    @Transient
    private int masteryLevel;
}
