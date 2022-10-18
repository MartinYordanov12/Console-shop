package com.coherentsolutions.store.utils;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.domain.Product;
import com.github.javafaker.Faker;

public class RandomFakeProductGenerator {
    private static final Integer PRICE_MIN = 1;
    private static final Integer PRICE_MAX = 50;
    private static final Integer RATE_MIN = 1;
    private static final Integer RATE_MAX = 6;

    private static final Faker GENERATOR = new Faker();

    private static Product createFakeProduct() {
        int price = GENERATOR.number().numberBetween(PRICE_MIN, PRICE_MAX);
        int rating = GENERATOR.number().numberBetween(RATE_MIN, RATE_MAX);
        String name = GENERATOR.name().firstName();
        return new Product(name, price, rating);
    }

    public static void generateFakeProductsForCatalog(Catalog catalog, int productsCount) {
        catalog.getCategories().forEach(x -> {
            for (int i = 0; i < productsCount; i++) {
                x.addProduct(createFakeProduct());
            }
        });
    }
}

