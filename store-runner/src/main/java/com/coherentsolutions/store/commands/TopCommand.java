package com.coherentsolutions.store.commands;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.service.CatalogService;
import com.coherentsolutions.store.utils.PrintingLine;

public class TopCommand implements Command {
    private static final String NAME = "top";
    private static final Integer SEPARATOR = 27;
    private final CatalogService catalogService;
    private final Catalog catalog;
    private final PrintingLine printingLine;

    public TopCommand(PrintingLine printingLine, Catalog catalog) {
        this.printingLine = printingLine;
        this.catalog = catalog;
        this.catalogService = new CatalogService();
    }

    @Override
    public void execute() {
        printingLine.printCommandCustomLine(NAME, SEPARATOR);
        System.out.println(catalogService.createListOfTopFiveProducts(catalog));

    }
}