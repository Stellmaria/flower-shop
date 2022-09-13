package com.it_academy.flower_shop.entity.model.flower;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
@NotNull
public class Pion extends Flower {
    private final String country;

    public Pion(String name, int freshness, double length, double price, String country) {
        super(name, freshness, length, price);
        this.country = country;
    }

    @Override
    public String toString() {
        return format("Name: %s | " + "Freshness: %d | " + "length: %.2f | " + "Price: %.2f | " + "Country: %s",
                getName(), getFreshness(), getLength(), getPrice(), country);
    }
}
