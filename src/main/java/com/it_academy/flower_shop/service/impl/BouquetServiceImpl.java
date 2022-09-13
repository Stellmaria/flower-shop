package com.it_academy.flower_shop.service.impl;

import com.it_academy.flower_shop.entity.model.flower.Flower;
import com.it_academy.flower_shop.service.BouquetService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Anastasia Melnikova
 */
public final class BouquetServiceImpl implements BouquetService {
    @Override
    public List<Flower> sortedFlower(@NotNull List<Flower> flowers) {
        return flowers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Flower> findFlower(@NotNull List<Flower> flowers,
                                       double minimumLength,
                                       double maximumLength) {
        return flowers.stream()
                .filter(flower -> flower.getLength() >= minimumLength && flower.getLength() <= maximumLength)
                .findFirst();
    }
}
