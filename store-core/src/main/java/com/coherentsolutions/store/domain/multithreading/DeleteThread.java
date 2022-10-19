package com.coherentsolutions.store.core.multithreading;

import com.coherentsolutions.store.core.service.PurchasedGoodsService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteThread extends Thread {
    PurchasedGoodsService purchasedGoodsService;
    Logger logger = Logger.getLogger(DeleteThread.class.getName());

    public DeleteThread(PurchasedGoodsService purchasedGoodsService) {
        this.purchasedGoodsService = purchasedGoodsService;
    }

    @Override
    public void run() {
        purchasedGoodsService.removeAllProducts();
        logger.log(Level.INFO, "Purchased products have been deleted");
    }
}