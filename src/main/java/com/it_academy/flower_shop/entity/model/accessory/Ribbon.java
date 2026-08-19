package com.it_academy.flower_shop.entity.model.accessory;

import lombok.EqualsAndHashCode;

import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
public class Ribbon extends Accessory {
    private final String decor;

    public Ribbon(String name, double length, double price, String decor) {
        super(name, length, price);
        this.decor = Objects.requireNonNull(decor, "decor must not be null");
    }

    @Override
    public String toString() {
        return format(Locale.ROOT,
                "Name: %s | Length: %.2f | Price: %.2f | Decor: %s",
                getName(), getLength(), getPrice(), decor);
    }
}
