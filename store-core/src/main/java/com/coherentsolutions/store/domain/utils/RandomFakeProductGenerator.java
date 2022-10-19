package com.coherentsolutions.store.core.utils;

import com.coherentsolutions.store.core.Catalog;
import com.coherentsolutions.store.core.Product;
import com.github.javafaker.Faker;

public class RandomFakeProductGenerator {
    private static final Integer PRICE_MIN = 1;
    private static final Integer PRICE_MAX = 50;
    private static final Integer RATE_MIN = 1;
    private static final Integer RATE_MAX = 6;

    private final Faker GENERATOR = new Faker();

    public void generateFakeProductsForCatalog(Catalog catalog, int productsCount) {
        catalog.getCategories().forEach(x -> {
            for (int i = 0; i < productsCount; i++) {
                x.addProduct(createFakeProduct());
            }
        });
    }

    private Product createFakeProduct() {
        int price = GENERATOR.number().numberBetween(PRICE_MIN, PRICE_MAX);
        int rating = GENERATOR.number().numberBetween(RATE_MIN, RATE_MAX);
        String name = GENERATOR.name().firstName();
        return new Product(name, price, rating);
    }
}