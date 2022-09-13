package com.it_academy.flower_shop.entity.model.accessory;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
public class WrappingPaper extends Accessory {
    private final String material;

    public WrappingPaper(String name, double length, double price, @NotNull String material) {
        super(name, length, price);
        this.material = material;
    }

    @Override
    public String toString() {
        return format("Name: %s | " + "Length: %.2f | " + "Price: %.2f | " + "Material: %s",
                getName(), getLength(), getPrice(), material);
    }
}
