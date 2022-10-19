package com.coherentsolutions.store.core.service;

import com.coherentsolutions.store.core.Catalog;
import com.coherentsolutions.store.core.Category;
import com.coherentsolutions.store.core.Product;
import com.coherentsolutions.store.core.exceptions.CategoryException;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CatalogService {

    private static final String SORTING_ORDER = "asc";
    private static final Integer LIMIT = 5;
    private static CategoryService categoryService;

    public CatalogService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Catalog createCatalog() {
        final List<Category> categories = categoryService.findAllCategories();
        return new Catalog(categories);
    }

    public static Catalog createNewCopy(Catalog catalogShop) {
        List<Category> categories = catalogShop.getCategories().stream()
                .map(SerializationUtils::clone)
                .collect(Collectors.toList());

        return new Catalog(categories);
    }

    public static Catalog createSortedCatalogByCriteriaFromSortRules(Catalog catalog, Map<String, String> criteriaInfo) {
        Map<String, Comparator<Product>> sortedCurrentComparator = sortingProducts(criteriaInfo);
        catalog.getCategories().forEach(category -> {
            List<String> listOfCriteria = new ArrayList<>(sortedCurrentComparator.keySet());
            Comparator<Product> mixedComparator = listOfCriteria.stream()
                    .map(sortedCurrentComparator::get)
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(NullPointerException::new);
            category.getProducts().sort(mixedComparator);
        });
        return catalog;
    }

    public List<Product> createListOfTopFiveProducts(Catalog catalog) {
        List<Product> flatProductsList = new ArrayList<>();
        for (Category list : catalog.getCategories()) {
            flatProductsList.addAll(list.getProducts());
        }
        flatProductsList.sort((o1, o2) -> Integer.compare(o2.price(), o1.price()));
        return flatProductsList.stream().limit(LIMIT).collect(Collectors.toList());
    }

    private static Map<String, Comparator<Product>> sortingProducts(Map<String, String> criteriaInformation) {
        Map<String, Comparator<Product>> currentComparator = new LinkedHashMap<>();
        Map<String, String> criteriaInfo = criteriaInformation;
        criteriaInfo.forEach((field, sortingOrder) -> {
            try {
                Method method = Product.class.getMethod(field);
                if (!isReturnTypeComparable(method))
                    throw new RuntimeException(String.format("Method return type %s is not comparable", method.getReturnType()));
                else {
                    Function<Product, Comparable<Object>> function = (Product p) -> {
                        try {
                            return (Comparable<Object>) method.invoke(p);
                        } catch (IllegalAccessException e) {
                            throw new CategoryException(String.format("There are IllegalAccessException from CatalogRuleSorter class problem with %s method %s", method.getName(), e.getMessage()));
                        } catch (InvocationTargetException e) {
                            throw new CategoryException(String.format("There are InvocationTargetException class problem with %s method %s", method.getName(), e.getMessage()));
                        }
                    };
                    Comparator<Product> comparing = Comparator.comparing(function, sortingOrder.equals(SORTING_ORDER) ? Comparator.naturalOrder() : Comparator.reverseOrder());
                    currentComparator.put(field, comparing);
                }
            } catch (java.lang.NoSuchMethodException e) {
                throw new CategoryException(String.format("There are NoSuchMethodException from CatalogRuleSorter class problems with method for field %s is not found %s", field, e.getMessage()));
            }
        });
        return currentComparator;
    }

    private static boolean isReturnTypeComparable(Method method) {
        return Comparable.class.isAssignableFrom(method.getReturnType()) || Comparable.class.isAssignableFrom(MethodType.methodType(method.getReturnType()).wrap().returnType());
    }
}