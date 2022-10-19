package com.coherentsolutions.store.domain.service;

import com.coherentsolutions.store.core.Catalog;
import com.coherentsolutions.store.core.Product;
import com.coherentsolutions.store.core.multithreading.ProcessingThread;

public class OrderService {
    Catalog catalog;
    PurchasedGoodsService purchasedGoodsService;

    public OrderService(Catalog catalog, PurchasedGoodsService purchasedGoodsService) {
        this.catalog = catalog;
        this.purchasedGoodsService = purchasedGoodsService;
    }

    public void createOrder(Product product) {
        ProcessingThread processingThread = new ProcessingThread(product, purchasedGoodsService);
        processingThread.start();
    }
}