package by.peachack.wim.model.items;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("PRIME_SET")
public class PrimeSet extends Item {
    @OneToMany
    private List<PrimePart> primePartList;
    @Transient
    private int ducatsPrice;
    @Transient
    private int masteryLevel;
}
