package com.coherentsolutions.store.domain.service;

import com.coherentsolutions.store.core.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedGoodsService {
    private final List<Product> purchasedGoods = Collections.synchronizedList(new ArrayList<>());

    public void addProduct(Product product) {
        purchasedGoods.add(product);
    }

    public void removeAllProducts() {
        purchasedGoods.clear();
    }

    public List<Product> getPurchasedGoods() {
        return purchasedGoods;
    }
}