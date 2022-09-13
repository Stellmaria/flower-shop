package com.it_academy.flower_shop.entity.model.bouquet;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.entity.model.accessory.Accessory;
import com.it_academy.flower_shop.entity.model.flower.Flower;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
@NotNull
public class Bouquet extends ShopItem implements Price {
    private final Collection<Flower> flowerList;
    private final Collection<Accessory> accessoryList;

    public Bouquet(String name, Collection<Flower> flowers, Collection<Accessory> accessories) {
        super(name);
        this.flowerList = new ArrayList<>(flowers);
        this.accessoryList = new ArrayList<>(accessories);
    }

    public Bouquet(Collection<Flower> flowers) {
        this("", flowers, new ArrayList<>());
    }

    public Bouquet(String name, Collection<Accessory> accessories) {
        this(name, new ArrayList<>(), new ArrayList<>(accessories));
    }

    public Bouquet(Flower flower) {
        this("", new ArrayList<>(List.of(flower)), new ArrayList<>());
    }

    public Bouquet(Accessory accessory) {
        this("", new ArrayList<>(), new ArrayList<>(List.of(accessory)));
    }

    public Bouquet(Flower flower, Accessory accessory) {
        this("", new ArrayList<>(List.of(flower)), new ArrayList<>(List.of(accessory)));
    }

    @Override
    public double getPrice() {
        return flowerList.stream()
                .mapToDouble(Flower::getPrice)
                .sum()
                + accessoryList.stream()
                .mapToDouble(accessory -> accessory.getPrice() * accessory.getLength())
                .sum();
    }

    public Collection<Flower> getFlowerList() {
        return new ArrayList<>(flowerList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(
                format("%nBouquet | Name: %s | Price: %.2f$%n", getName(), getPrice()));

        flowerList.forEach(flower -> stringBuilder
                .append(flower)
                .append(format("%n")));

        stringBuilder.append(format("%n"));

        accessoryList.forEach(accessory -> stringBuilder
                .append(accessory)
                .append(format("%n")));

        return stringBuilder.toString();
    }
}
