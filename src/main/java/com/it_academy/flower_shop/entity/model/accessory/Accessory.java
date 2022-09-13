package com.it_academy.flower_shop.entity.model.accessory;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.service.Length;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Anastasia Melnikova
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class Accessory extends ShopItem implements Length, Price {
    private final double length;
    private final double price;

    protected Accessory(String name, double length, double price) {
        super(name);
        this.length = length;
        this.price = price;
    }
}
