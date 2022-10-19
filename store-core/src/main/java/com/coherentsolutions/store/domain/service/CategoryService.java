package com.coherentsolutions.store.core.service;

import com.coherentsolutions.store.core.dao.CategoryDao;
import com.coherentsolutions.store.core.dao.CategoryFinderDaoImpl;
import com.coherentsolutions.store.core.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final static String categoryPath = "com.coherentsolutions.store.core.categories";
    private final CategoryDao categoryDao = new CategoryFinderDaoImpl(categoryPath);

    public Optional<Category> find(String name) {
        return categoryDao.find(name);
    }

    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }
}