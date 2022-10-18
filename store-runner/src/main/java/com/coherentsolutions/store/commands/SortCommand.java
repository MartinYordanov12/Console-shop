package com.coherentsolutions.store.commands;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.reader.SortRulesFileReader;
import com.coherentsolutions.store.service.CatalogService;
import com.coherentsolutions.store.utils.PrintingLine;

import java.util.Map;

public class SortCommand implements Command {
    private static final String SORT_RULES_FILE = "sort-rules.xml";
    private static final String NAME = "sort";
    private static final Integer SEPARATOR = 26;
    private final Catalog catalog;
    private final PrintingLine printingLine;
    SortRulesFileReader sortRulesFileReader = new SortRulesFileReader();

    public SortCommand(PrintingLine printingLine, Catalog catalog) {
        this.printingLine = printingLine;
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        printingLine.printCommandCustomLine(NAME, SEPARATOR);
        Catalog deepCopyOfCatalog = CatalogService.createNewCopy(catalog);
        Map<String, String> criteriaInfo = sortRulesFileReader.readXmlTagNames(SORT_RULES_FILE);
        System.out.println(CatalogService.createSortedCatalogByCriteriaFromSortRules((deepCopyOfCatalog), criteriaInfo));
    }
}