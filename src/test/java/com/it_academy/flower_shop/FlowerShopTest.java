package com.it_academy.flower_shop;

import com.it_academy.flower_shop.entity.model.accessory.Accessory;
import com.it_academy.flower_shop.entity.model.accessory.Ribbon;
import com.it_academy.flower_shop.entity.model.accessory.WrappingPaper;
import com.it_academy.flower_shop.entity.model.bouquet.Bouquet;
import com.it_academy.flower_shop.entity.model.flower.Flower;
import com.it_academy.flower_shop.entity.model.flower.Lily;
import com.it_academy.flower_shop.entity.model.flower.Pion;
import com.it_academy.flower_shop.entity.model.flower.Rose;
import com.it_academy.flower_shop.service.impl.BouquetServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Bouquet testing.")
class FlowerShopTest {
    private static final double DELTA = 0.0001;
    private static BouquetServiceImpl bouquetService;

    private Bouquet bouquet;
    private Bouquet onlyFlower;
    private Lily lily;
    private Pion pion;
    private Rose rose;
    private Ribbon ribbon;
    private WrappingPaper wrappingPaper;

    @BeforeAll
    static void beforeAll() {
        bouquetService = new BouquetServiceImpl();
    }

    @BeforeEach
    void beforeEach() {
        lily = new Lily("Yellow lily", 50, 24, 5, "Hybrid");
        pion = new Pion("Black pion", 45, 18, 30, "USA");
        rose = new Rose("Red rose", 60, 30, 25, "Big");

        ribbon = new Ribbon("Pink ribbon", 10, 0.4, "Waves");
        wrappingPaper = new WrappingPaper("White wrapping paper", 15, 1.5, "Transparent");

        List<Flower> flowers = new ArrayList<>(of(lily, pion, pion, rose, rose));
        List<Accessory> accessories = new ArrayList<>(of(ribbon, wrappingPaper));

        bouquet = new Bouquet("Mix", flowers, accessories);
        onlyFlower = new Bouquet(flowers);
    }

    static Stream<Arguments> getTotalPriceProviderArguments() {
        return Stream.of(
                Arguments.of(24.0, new Bouquet(new ArrayList<>(of(
                        new Lily("White lily", 50, 4, 24, "Hybrid"))))),
                Arguments.of(48.0, new Bouquet(new ArrayList<>(of(
                        new Lily("Yellow lily", 50, 4, 24, "Longiflorum"),
                        new Lily("Yellow lily", 50, 4, 24, "Tubular"))))),
                Arguments.of(17.0, new Bouquet(
                        new Pion("Red pion", 14, 4, 17, "Red"))),
                Arguments.of(312.0, new Bouquet(
                        new WrappingPaper("Black wrapping paper", 4, 78, "Rainbow"))),
                Arguments.of(98.0, new Bouquet("Mix", new ArrayList<>(of(
                        new WrappingPaper("Black wrapping paper", 14, 5, "Paper"),
                        new WrappingPaper("Red wrapping paper", 4, 7, "Paper"))))),
                Arguments.of(357.0, new Bouquet(
                        new Pion("White pion", 45, 1, 45, "USA"),
                        new WrappingPaper("Yellow wrapping paper", 4, 78, "Paper"))));
    }

    @DisplayName("Testing the total cost of the bouquet.")
    @MethodSource("getTotalPriceProviderArguments")
    @ParameterizedTest(name = "Expected {0}.")
    void getTotalPrice(double expected, Bouquet bouquets) {
        var actualMethod = bouquets.getPrice();

        assertAll(
                () -> assertEquals(expected, actualMethod, DELTA),
                () -> assertEquals(141.5, bouquet.getPrice(), DELTA),
                () -> assertEquals(115.0, onlyFlower.getPrice(), DELTA)
        );
    }

    @Test
    @DisplayName("Sort the bouquet by freshness.")
    void sortFlowers() {
        var expected = of(
                new Pion("Black pion", 45, 18, 30, "USA"),
                new Pion("Black pion", 45, 18, 30, "USA"),
                new Lily("Yellow lily", 50, 24, 5, "Hybrid"),
                new Rose("Red rose", 60, 30, 25, "Big"),
                new Rose("Red rose", 60, 30, 25, "Big")
        );

        var actual = bouquetService.sortedFlower(bouquet.getFlowerList());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Looking for a flower along the length of the stem.")
    void findFlower() {
        Optional<Flower> expected = Optional.of(
                new Lily("Yellow lily", 50, 24, 5, "Hybrid"));

        var actual = bouquetService.findFlower(bouquet.getFlowerList(), 22, 26);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Reject invalid search ranges.")
    void rejectInvalidSearchRange() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> bouquetService.findFlower(bouquet.getFlowerList(), 30, 20)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> bouquetService.findFlower(bouquet.getFlowerList(), -1, 20)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> bouquetService.findFlower(bouquet.getFlowerList(), 10, Double.NaN))
        );
    }

    @Test
    @DisplayName("Reject invalid domain values.")
    void rejectInvalidDomainValues() {
        assertAll(
                () -> assertThrows(NullPointerException.class,
                        () -> new Lily(null, 50, 24, 5, "Hybrid")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Lily("Lily", -1, 24, 5, "Hybrid")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Lily("Lily", 50, -1, 5, "Hybrid")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Lily("Lily", 50, 24, -1, "Hybrid")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new Ribbon("Ribbon", 10, Double.POSITIVE_INFINITY, "Waves"))
        );
    }

    @Test
    @DisplayName("Bouquet exposes defensive copies of its flowers.")
    void flowerListIsDefensiveCopy() {
        var copy = bouquet.getFlowerList();
        copy.clear();

        assertEquals(5, bouquet.getFlowerList().size());
    }

    @Test
    @DisplayName("Comparison of different bouquets.")
    void comparisonBouquets() {
        assertAll(
                () -> assertNotEquals(onlyFlower.hashCode(), bouquet.hashCode()),
                () -> assertNotEquals(onlyFlower, bouquet));
    }

    @Test
    @DisplayName("Comparison of different lily.")
    void comparisonFlowerLily() {
        var difficult = new Lily("Yellow lily", 45, 5, 35, "Hybrid");

        assertAll(
                () -> assertNotEquals(lily, difficult),
                () -> assertNotEquals(lily.hashCode(), difficult.hashCode()));
    }

    @Test
    @DisplayName("Comparison of different pion.")
    void comparisonFlowerPion() {
        var difficult = new Pion("Black pion", 45, 4, 4, "USA");

        assertAll(
                () -> assertNotEquals(pion, difficult),
                () -> assertNotEquals(pion.hashCode(), difficult.hashCode()));
    }

    @Test
    @DisplayName("Comparison of different rose.")
    void comparisonFlowerRose() {
        var difficult = new Rose("Red rose", 45, 4, 4, "Small");

        assertAll(
                () -> assertNotEquals(rose, difficult),
                () -> assertNotEquals(rose.hashCode(), difficult.hashCode()));
    }

    @Test
    @DisplayName("Comparison of different ribbon.")
    void comparisonAccessoryRibbon() {
        var difficult = new Ribbon("Pink ribbon", 4, 4, "Mugs");

        assertAll(
                () -> assertNotEquals(ribbon, difficult),
                () -> assertNotEquals(ribbon.hashCode(), difficult.hashCode()));
    }

    @Test
    @DisplayName("Comparison of different wrapping paper.")
    void comparisonAccessoryWrappingPaper() {
        var difficult = new WrappingPaper("Yellow wrapping paper", 10, 14, "Paper");

        assertAll(
                () -> assertNotEquals(wrappingPaper, difficult),
                () -> assertNotEquals(wrappingPaper.hashCode(), difficult.hashCode()));
    }

    @Test
    @DisplayName("Ribbon description.")
    void printRibbon() {
        var expected = "Name: Pink ribbon | Length: 10.00 | Price: 0.40 | Decor: Waves";

        assertEquals(expected, ribbon.toString());
    }

    @Test
    @DisplayName("Wrapping paper description.")
    void printWrappingPaper() {
        var expected = "Name: White wrapping paper | Length: 15.00 | Price: 1.50 | Material: Transparent";

        assertEquals(expected, wrappingPaper.toString());
    }

    @Test
    @DisplayName("Lily description.")
    void printLily() {
        var expected = "Name: Yellow lily | Freshness: 50 | length: 24.00 | Price: 5.00 | Type: Hybrid";

        assertEquals(expected, lily.toString());
    }

    @Test
    @DisplayName("Pion description.")
    void printPion() {
        var expected = "Name: Black pion | Freshness: 45 | length: 18.00 | Price: 30.00 | Country: USA";

        assertEquals(expected, pion.toString());
    }

    @Test
    @DisplayName("Rose description.")
    void printRose() {
        var expected = "Name: Red rose | Freshness: 60 | length: 30.00 | Price: 25.00 | Thorn: Big";

        assertEquals(expected, rose.toString());
    }

    private static String boughtDescriptions() {
        return format("%nBouquet | Name: Mix | Price: 141.50$%n" +
                "Name: Yellow lily | Freshness: 50 | length: 24.00 | Price: 5.00 | Type: Hybrid%n" +
                "Name: Black pion | Freshness: 45 | length: 18.00 | Price: 30.00 | Country: USA%n" +
                "Name: Black pion | Freshness: 45 | length: 18.00 | Price: 30.00 | Country: USA%n" +
                "Name: Red rose | Freshness: 60 | length: 30.00 | Price: 25.00 | Thorn: Big%n" +
                "Name: Red rose | Freshness: 60 | length: 30.00 | Price: 25.00 | Thorn: Big%n%n" +
                "Name: Pink ribbon | Length: 10.00 | Price: 0.40 | Decor: Waves%n" +
                "Name: White wrapping paper | Length: 15.00 | Price: 1.50 | Material: Transparent%n");
    }

    private static String boughtOnlyFlowerDescriptions() {
        return format("%nBouquet | Name:  | Price: 115.00$%n" +
                "Name: Yellow lily | Freshness: 50 | length: 24.00 | Price: 5.00 | Type: Hybrid%n" +
                "Name: Black pion | Freshness: 45 | length: 18.00 | Price: 30.00 | Country: USA%n" +
                "Name: Black pion | Freshness: 45 | length: 18.00 | Price: 30.00 | Country: USA%n" +
                "Name: Red rose | Freshness: 60 | length: 30.00 | Price: 25.00 | Thorn: Big%n" +
                "Name: Red rose | Freshness: 60 | length: 30.00 | Price: 25.00 | Thorn: Big%n%n");
    }

    @Test
    @DisplayName("Bouquet description.")
    void printBouquet() {
        var firstExpected = boughtDescriptions();
        var secondExpected = boughtOnlyFlowerDescriptions();

        assertAll(
                () -> assertEquals(firstExpected, bouquet.toString()),
                () -> assertEquals(secondExpected, onlyFlower.toString())
        );
    }
}
