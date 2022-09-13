package com.it_academy.flower_shop.entity;

import com.it_academy.flower_shop.service.Name;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Anastasia Melnikova
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@NotNull
public abstract class ShopItem implements Name {
    private final String name;
}
