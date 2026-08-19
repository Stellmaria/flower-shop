package com.it_academy.flower_shop.service.impl;

import com.it_academy.flower_shop.entity.model.flower.Flower;
import com.it_academy.flower_shop.service.BouquetService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Anastasia Melnikova
 */
public final class BouquetServiceImpl implements BouquetService {
    @Override
    public List<Flower> sortedFlower(List<Flower> flowers) {
        Objects.requireNonNull(flowers, "flowers must not be null");
        return flowers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Flower> findFlower(List<Flower> flowers,
                                       double minimumLength,
                                       double maximumLength) {
        Objects.requireNonNull(flowers, "flowers must not be null");
        validateRange(minimumLength, maximumLength);
        return flowers.stream()
                .filter(flower -> flower.getLength() >= minimumLength && flower.getLength() <= maximumLength)
                .findFirst();
    }

    private static void validateRange(double minimumLength, double maximumLength) {
        if (!Double.isFinite(minimumLength) || !Double.isFinite(maximumLength)
                || minimumLength < 0 || maximumLength < 0) {
            throw new IllegalArgumentException("length range must contain finite non-negative values");
        }
        if (minimumLength > maximumLength) {
            throw new IllegalArgumentException("minimumLength must not exceed maximumLength");
        }
    }
}
