package com.it_academy.flower_shop.entity.model.flower;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
@NotNull
public class Rose extends Flower {
    private final String thorn;

    public Rose(String name, int freshness, double length, double price, String thorn) {
        super(name, freshness, length, price);
        this.thorn = thorn;
    }

    @Override
    public String toString() {
        return format("Name: %s | " + "Freshness: %d | " + "length: %.2f | " + "Price: %.2f | " + "Thorn: %s",
                getName(), getFreshness(), getLength(), getPrice(), thorn);
    }
}
