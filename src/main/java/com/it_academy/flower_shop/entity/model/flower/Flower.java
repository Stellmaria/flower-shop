package com.it_academy.flower_shop.entity.model.flower;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.service.Length;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.compare;

/**
 * @author Anastasia Melnikova
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class Flower extends ShopItem implements Comparable<Flower>, Length, Price {
    private final int freshness;
    private final double length;
    private final double price;

    protected Flower(String name, int freshness, double length, double price) {
        super(name);
        this.freshness = freshness;
        this.length = length;
        this.price = price;
    }

    @Override
    public int compareTo(@NotNull Flower o) {
        return compare(getFreshness(), o.getFreshness());
    }
}
