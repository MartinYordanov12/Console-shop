package com.coherentsolutions.store.domain;

import java.util.List;

public record Catalog(List<Category> categories) {

public List<Category> getCategories() {
        return List.copyOf(categories);
        }

@Override
public String toString() {
        return String.valueOf(categories);
        }
        }

