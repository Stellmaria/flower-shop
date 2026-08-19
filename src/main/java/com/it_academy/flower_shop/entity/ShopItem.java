package com.it_academy.flower_shop.entity;

import com.it_academy.flower_shop.service.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

/**
 * Base type for every item that can appear in the flower shop domain.
 *
 * @author Anastasia Melnikova
 */
@Getter
@EqualsAndHashCode
public abstract class ShopItem implements Name {
    private final String name;

    protected ShopItem(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }
}
