package com.it_academy.flower_shop.entity.model.accessory;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
@NotNull
public class Ribbon extends Accessory {
    private final String decor;

    public Ribbon(String name, double length, double price, String decor) {
        super(name, length, price);
        this.decor = decor;
    }

    @Override
    public String toString() {
        return format("Name: %s | " + "Length: %.2f | " + "Price: %.2f | " + "Decor: %s",
                getName(), getLength(), getPrice(), decor);
    }
}
