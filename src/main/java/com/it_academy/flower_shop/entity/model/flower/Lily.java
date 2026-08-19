package com.it_academy.flower_shop.entity.model.flower;

import lombok.EqualsAndHashCode;

import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
public class Lily extends Flower {
    private final String type;

    public Lily(String name, int freshness, double length, double price, String type) {
        super(name, freshness, length, price);
        this.type = Objects.requireNonNull(type, "type must not be null");
    }

    @Override
    public String toString() {
        return format(Locale.ROOT,
                "Name: %s | Freshness: %d | length: %.2f | Price: %.2f | Type: %s",
                getName(), getFreshness(), getLength(), getPrice(), type);
    }
}
