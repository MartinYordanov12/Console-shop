package com.coherentsolutions.store;

import com.coherentsolutions.store.interaction.UserInputDispatcher;
import com.coherentsolutions.store.core.Catalog;
import com.coherentsolutions.store.core.service.CatalogService;
import com.coherentsolutions.store.core.service.CategoryService;
import com.coherentsolutions.store.core.utils.RandomFakeProductGenerator;

public class StoreApp {
    private final static String RIGHT_BRACKET = "[";
    private final static String COMMA = ",";
    private final static String LEFT_BRACKET = "]";
    private final static String QUOTES = "";
    private static final Integer PRODUCT_NUMBER = 4;

    public void run() {
        CategoryService categoryService = new CategoryService();
        CatalogService catalogCreator = new CatalogService(categoryService);
        Catalog catalog = catalogCreator.createCatalog();
        RandomFakeProductGenerator.generateFakeProductsForCatalog(catalog, PRODUCT_NUMBER);
        System.out.println(catalog.toString()
                .replace(RIGHT_BRACKET, QUOTES)
                .replace(COMMA, QUOTES)
                .replace(LEFT_BRACKET, QUOTES));
        UserInputDispatcher storeService = new UserInputDispatcher(catalog);
        storeService.dispatch();
    }
    public static void main(String[] args) {
        StoreApp appRun = new StoreApp();
        appRun.run();
    }
}