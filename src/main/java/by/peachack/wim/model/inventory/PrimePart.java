package by.peachack.wim.model.inventory;

import jakarta.persistence.*;


@Entity
@Table(name = "prime_parts")
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

}
