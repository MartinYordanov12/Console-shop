package com.coherentsolutions.store.service;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.domain.Product;
import com.github.javafaker.Faker;

public class RandomFakeProductGenerator {
    private static final Faker GENERATOR = new Faker();

    private static Product createFakeProduct() {
        int price = GENERATOR.number().numberBetween(1, 50);
        int rating = GENERATOR.number().numberBetween(1, 6);
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

