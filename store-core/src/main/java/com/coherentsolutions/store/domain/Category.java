package com.coherentsolutions.store.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String name;
    private final List<Product> products = new ArrayList<>();

    public Category(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(final Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format("%s %s %n",name,products);

    }
}
