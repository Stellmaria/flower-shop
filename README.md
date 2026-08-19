# Flower Shop

Educational Java project that models a flower shop bouquet and the operations from the course assignment.

## Implemented features

- flower hierarchy with `Lily`, `Pion` and `Rose`;
- bouquet accessories with `Ribbon` and `WrappingPaper`;
- bouquet creation from flowers, accessories, or both;
- total bouquet price calculation;
- sorting flowers by freshness;
- searching for a flower by stem-length range;
- value validation for names, freshness, length and price;
- deterministic text formatting independent of the operating-system locale;
- JUnit 5 tests for pricing, sorting, searching, equality, formatting and invalid inputs.

## Requirements

- Java 11 or newer;
- Maven 3.8+.

## Build and test

```bash
mvn clean test
```

Run the full verification lifecycle with:

```bash
mvn clean verify
```

GitHub Actions runs `mvn -B clean verify` on pushes to `master` and feature branches, and on pull requests targeting `master`.

## Project structure

```text
src/main/java/com/it_academy/flower_shop/
├── entity/
│   ├── ShopItem.java
│   └── model/
│       ├── accessory/
│       ├── bouquet/
│       └── flower/
└── service/
    ├── BouquetService.java
    └── impl/BouquetServiceImpl.java

src/test/java/com/it_academy/flower_shop/
└── FlowerShopTest.java
```

## Notes

This is a learning project. Prices are intentionally represented with `double` because the assignment focuses on OOP, collections and testing rather than production-grade monetary calculations. A real commerce application should use a decimal money type such as `BigDecimal` and a dedicated currency model.
