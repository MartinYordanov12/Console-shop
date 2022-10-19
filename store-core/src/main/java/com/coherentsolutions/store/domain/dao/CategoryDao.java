package com.coherentsolutions.store.core.dao;

import com.coherentsolutions.store.core.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    Optional<Category> find(String name);

    List<Category> findAllCategories();
}