package com.it_academy.flower_shop.entity.model.accessory;

import lombok.EqualsAndHashCode;

import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
public class WrappingPaper extends Accessory {
    private final String material;

    public WrappingPaper(String name, double length, double price, String material) {
        super(name, length, price);
        this.material = Objects.requireNonNull(material, "material must not be null");
    }

    @Override
    public String toString() {
        return format(Locale.ROOT,
                "Name: %s | Length: %.2f | Price: %.2f | Material: %s",
                getName(), getLength(), getPrice(), material);
    }
}
