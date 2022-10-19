package com.coherentsolutions.store.core.dao;

import com.coherentsolutions.store.core.Category;
import com.coherentsolutions.store.core.exceptions.CategoryException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryFinderDaoImpl implements com.coherentsolutions.store.core.dao.CategoryDao {
    private final String categoriesPath;
    private List<Category> categoryList = null;

    public CategoryFinderDaoImpl(String categoriesPackage) {
        this.categoriesPath = categoriesPackage;
    }

    @Override
    public Optional<Category> find(String name) {

        return findAllCategories().stream()
                .filter(x -> x.getName().equals(name))
                .findFirst();
    }

    public List<Category> findAllCategories() {
        if (categoryList == null) {
            categoryList = loadClassesFromPackage(categoriesPath);
        }
        return categoryList;
    }

    private List<Category> loadClassesFromPackage(final String categoriesPackage) {
        Reflections reflections = new Reflections(categoriesPackage);
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);

        return subTypes.stream()
                .map(type -> {
                    try {
                        return type.getConstructor().newInstance();
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                        throw new CategoryException(String.format("New category %s can not be created!", type.getName()));
                    }
                }).collect(Collectors.toList());
    }
}