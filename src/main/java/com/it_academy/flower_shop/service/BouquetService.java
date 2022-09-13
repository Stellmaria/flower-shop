package com.it_academy.flower_shop.service;

import com.it_academy.flower_shop.entity.model.flower.Flower;

import java.util.List;
import java.util.Optional;

/**
 * @author Anastasia Melnikova
 */
public interface BouquetService {

    /**
     * Sorts flowers according to their freshness from smallest to largest.
     *
     * @param flowers list of colors to sort.
     * @return sorted list of flowers.
     */
    List<Flower> sortedFlower(List<Flower> flowers);

    /**
     * Searches for a flower in the given range.
     *
     * @param flowers       list of colors to sort.
     * @param minimumLength minimum range limit.
     * @param maximumLength maximum limit of the range.
     * @return found flower.
     */

    Optional<Flower> findFlower(List<Flower> flowers, double minimumLength, double maximumLength);
}

