package com.coherentsolutions.store.service;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.domain.Category;
import com.coherentsolutions.store.domain.Product;
import exceptions.CatalogException;
import java.util.List;

public class CatalogService {
    private final CatalogReflectionLibLoader catalogReflectionLibLoader = new CatalogReflectionLibLoader();

    public Catalog createCatalog(final String categoriesPackage) {
        final List<Category> categories = catalogReflectionLibLoader.loadClassesFromPackage(categoriesPackage);

        return new Catalog(categories);
    }

    public void addProductToCategory(final Catalog catalog, final Product product, final String category) {
        catalog.getCategories().stream()
                .filter(listCategory -> listCategory.getName().equals(category))
                .findAny()
                .ifPresentOrElse(listCategory -> listCategory.addProduct(product),
                        () -> {
                            throw new CatalogException("There is no such category");
                        });
    }
}
