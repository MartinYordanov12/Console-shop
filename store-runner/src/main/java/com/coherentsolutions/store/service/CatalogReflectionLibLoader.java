package com.coherentsolutions.store.service;

import com.coherentsolutions.store.domain.Category;
import com.coherentsolutions.store.exceptions.CategoryException;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogReflectionLibLoader {
    public List<Category> loadClassesFromPackage(final String categoriesPackage) {
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

