package com.coherentsolutions.store.domain.service;

import com.coherentsolutions.store.core.multithreading.DeleteThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorService {
    ScheduledExecutorService scheduledExecutorService;
    PurchasedGoodsService purchasedGoodsService;

    public ExecutorService(PurchasedGoodsService purchasedGoodsService) {
        this.purchasedGoodsService = purchasedGoodsService;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    }

    public void startScheduleExecutor() {
        scheduledExecutorService.scheduleAtFixedRate(new DeleteThread(purchasedGoodsService), 0, 10, TimeUnit.SECONDS);
    }

    public void shutDownScheduleExecutor() {
        scheduledExecutorService.shutdown();
    }
}