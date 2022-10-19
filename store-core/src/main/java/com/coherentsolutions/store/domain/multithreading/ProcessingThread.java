package com.coherentsolutions.store.core.multithreading;

import com.coherentsolutions.store.core.Product;
import com.coherentsolutions.store.core.service.PurchasedGoodsService;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcessingThread extends Thread {
    private static final int SLEEPING_THREAD_TIME = 30000;
    private final Product product;
    private final PurchasedGoodsService purchasedGoodsService;
    private final Logger logger = Logger.getLogger(ProcessingThread.class.getName());

    public ProcessingThread(Product product, PurchasedGoodsService purchasedGoodsService) {
        this.product = product;
        this.purchasedGoodsService = purchasedGoodsService;
    }

    @Override
    public void run() {
        logger.info("Purchase order " + Thread.currentThread() + " is starting...");
        sleepingThread();
        purchasedGoodsService.addProduct(product);
        logger.info("Purchase order " + Thread.currentThread() + " is finished...");

    }
    private void sleepingThread(){
        logger.setLevel(Level.INFO);
        Random random = new Random();
        try {
            int sleepTime = random.nextInt(ProcessingThread.SLEEPING_THREAD_TIME);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.info("ProcessingThread has been interrupted");
        }
    }
}