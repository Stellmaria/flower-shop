package com.it_academy.flower_shop.entity.model.accessory;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.service.Length;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Base class for bouquet accessories sold by length.
 *
 * @author Anastasia Melnikova
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class Accessory extends ShopItem implements Length, Price {
    private final double length;
    private final double price;

    protected Accessory(String name, double length, double price) {
        super(name);
        requireNonNegativeFinite(length, "length");
        requireNonNegativeFinite(price, "price");
        this.length = length;
        this.price = price;
    }

    private static void requireNonNegativeFinite(double value, String fieldName) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(fieldName + " must be a finite non-negative number");
        }
    }
}
