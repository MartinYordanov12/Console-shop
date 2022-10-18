package com.coherentsolutions.store.utils;

public class PrintingLine {
    private static final String SEPARATOR = "*";
    private static final String PRINT = "%s %s %s%n";

    public void printCommandCustomLine(String lineName, int separator) {
        String separatorLine = SEPARATOR.repeat(separator);
        System.out.printf(PRINT, separatorLine, lineName, separatorLine);
    }
}