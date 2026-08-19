package com.it_academy.flower_shop.entity.model.flower;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.service.Length;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

import static java.lang.Integer.compare;

/**
 * Base class for flowers used in bouquets.
 *
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
        if (freshness < 0) {
            throw new IllegalArgumentException("freshness must be non-negative");
        }
        requireNonNegativeFinite(length, "length");
        requireNonNegativeFinite(price, "price");
        this.freshness = freshness;
        this.length = length;
        this.price = price;
    }

    @Override
    public int compareTo(Flower other) {
        Objects.requireNonNull(other, "flower must not be null");
        return compare(getFreshness(), other.getFreshness());
    }

    private static void requireNonNegativeFinite(double value, String fieldName) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(fieldName + " must be a finite non-negative number");
        }
    }
}
