package by.peachack.wim.model.inventory;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "prime_parts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrimePart extends Item {
    @ManyToOne
    @JoinColumn(name = "set_id")
    private PrimeSet setItem;
    @Column(name = "quantity_for_set")
    private int quantityForSet;
    @Column(name = "mastery_lvl")
    private int masteryLevel;
    @Column(name = "ducats_price")
    private int ducatsPrice;

    public PrimePart(Item item, int quantityForSet, int masteryLevel, int ducatsPrice) {
        super(item);
        this.quantityForSet = quantityForSet;
        this.masteryLevel = masteryLevel;
        this.ducatsPrice = ducatsPrice;
    }
}
