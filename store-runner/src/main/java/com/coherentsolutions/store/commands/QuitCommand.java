package com.coherentsolutions.store.commands;

import com.coherentsolutions.store.utils.PrintingLine;

public class QuitCommand implements Command {
    private static final String NAME = "quit";
    private static final Integer SEPARATOR = 26;

    private final PrintingLine printingLine = new PrintingLine();

    @Override
    public void execute() {
        printingLine.printCommandCustomLine(NAME, SEPARATOR);
    }
}