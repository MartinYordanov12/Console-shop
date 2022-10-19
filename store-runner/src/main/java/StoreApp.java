package com.coherentsolutions.store;

import com.coherentsolutions.store.core.Catalog;
import com.coherentsolutions.store.core.service.CatalogService;
import com.coherentsolutions.store.core.service.CategoryService;
import com.coherentsolutions.store.core.service.ExecutorService;
import com.coherentsolutions.store.core.service.PurchasedGoodsService;
import com.coherentsolutions.store.core.utils.RandomFakeProductGenerator;
import com.coherentsolutions.store.interaction.UserInputDispatcher;

public class StoreApp {
    private static final Integer PRODUCT_NUMBER = 4;

    public static void main(String[] args) {
        StoreApp appRun = new StoreApp();
        appRun.run();
    }

    public void run() {
        PurchasedGoodsService purchasedGoodsService = new PurchasedGoodsService();
        ExecutorService executorService = new ExecutorService(purchasedGoodsService);
        CategoryService categoryService = new CategoryService();
        CatalogService catalogCreator = new CatalogService(categoryService);
        Catalog catalog = catalogCreator.createCatalog();
        UserInputDispatcher storeService = new UserInputDispatcher(catalog, purchasedGoodsService);
        RandomFakeProductGenerator randomFakeProductGenerator = new RandomFakeProductGenerator();

        randomFakeProductGenerator.generateFakeProductsForCatalog(catalog, PRODUCT_NUMBER);
        System.out.println(catalog);

        executorService.startScheduleExecutor();
        storeService.dispatch();
        executorService.shutDownScheduleExecutor();
    }
}