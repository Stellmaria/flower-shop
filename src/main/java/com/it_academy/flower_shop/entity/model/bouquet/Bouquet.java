package com.it_academy.flower_shop.entity.model.bouquet;

import com.it_academy.flower_shop.entity.ShopItem;
import com.it_academy.flower_shop.entity.model.accessory.Accessory;
import com.it_academy.flower_shop.entity.model.flower.Flower;
import com.it_academy.flower_shop.service.Price;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

/**
 * A bouquet containing flowers and optional accessories.
 *
 * @author Anastasia Melnikova
 */
@EqualsAndHashCode(callSuper = true)
public class Bouquet extends ShopItem implements Price {
    private final List<Flower> flowerList;
    private final List<Accessory> accessoryList;

    public Bouquet(String name, Collection<Flower> flowers, Collection<Accessory> accessories) {
        super(name);
        this.flowerList = List.copyOf(Objects.requireNonNull(flowers, "flowers must not be null"));
        this.accessoryList = List.copyOf(Objects.requireNonNull(accessories, "accessories must not be null"));
    }

    public Bouquet(Collection<Flower> flowers) {
        this("", flowers, List.of());
    }

    public Bouquet(String name, Collection<Accessory> accessories) {
        this(name, List.of(), accessories);
    }

    public Bouquet(Flower flower) {
        this("", List.of(Objects.requireNonNull(flower, "flower must not be null")), List.of());
    }

    public Bouquet(Accessory accessory) {
        this("", List.of(), List.of(Objects.requireNonNull(accessory, "accessory must not be null")));
    }

    public Bouquet(Flower flower, Accessory accessory) {
        this("",
                List.of(Objects.requireNonNull(flower, "flower must not be null")),
                List.of(Objects.requireNonNull(accessory, "accessory must not be null")));
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

    public List<Flower> getFlowerList() {
        return new ArrayList<>(flowerList);
    }

    public List<Accessory> getAccessoryList() {
        return new ArrayList<>(accessoryList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(
                format(Locale.ROOT, "%nBouquet | Name: %s | Price: %.2f$%n", getName(), getPrice()));

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
