package com.it_academy.flower_shop.entity.model.flower;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
@NotNull
public class Lily extends Flower {
    private final String type;

    public Lily(String name, int freshness, double length, double price, String type) {
        super(name, freshness, length, price);
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | " + "Freshness: %d | " + "length: %.2f | " + "Price: %.2f | " + "Type: %s",
                getName(), getFreshness(), getLength(), getPrice(), type);
    }
}
