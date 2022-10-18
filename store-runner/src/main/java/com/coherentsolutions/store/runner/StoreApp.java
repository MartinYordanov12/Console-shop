package com.coherentsolutions.store.runner;

import com.coherentsolutions.store.invoke.UserInputDispatcher;
import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.service.CatalogService;
import com.coherentsolutions.store.utils.RandomFakeProductGenerator;

public class StoreApp {
    private final static String CATEGORIES_PACKAGE = "com.coherentsolutions.store.domain.categories";
    private final static String RIGHT_BRACKET = "[";
    private final static String COMMA = ",";
    private final static String LEFT_BRACKET = "]";
    private final static String QUOTES = "";
    private static final Integer PRODUCT_NUMBER = 4;

    public static void main(String[] args) {
        CatalogService catalogCreator = new CatalogService();
        Catalog catalog = catalogCreator.createCatalog(CATEGORIES_PACKAGE);
        RandomFakeProductGenerator.generateFakeProductsForCatalog(catalog, PRODUCT_NUMBER);
        System.out.println(catalog.toString()
                .replace(RIGHT_BRACKET, QUOTES)
                .replace(COMMA, QUOTES)
                .replace(LEFT_BRACKET, QUOTES));
        UserInputDispatcher storeService = new UserInputDispatcher(catalog);
        storeService.dispatch();
    }
}