package com.coherentsolutions.store.commands;

import com.coherentsolutions.store.domain.Catalog;
import com.coherentsolutions.store.utils.PrintingLine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class UserInputDispatcher {
    private static final String USER_INPUT_SORT = "sort";
    private static final String USER_INPUT_TOP = "top";
    private static final String USER_INPUT_QUIT = "quit";
    private static final String NOT_SUPPORTED_OPERATION_MESSAGE = "Not supported operation";
    private static final PrintingLine printCommand = new PrintingLine();
    private final Map<String, Command> userInputCommand = new HashMap<>();
    private final CommandPerformer commandPerformer = new CommandPerformer();

    public UserInputDispatcher(Catalog catalog) {
        SortCommand sortCommand = new SortCommand(printCommand, catalog);
        TopCommand topCommand = new TopCommand(printCommand, catalog);
        QuitCommand quitCommand = new QuitCommand();
        userInputCommand.put(USER_INPUT_SORT, sortCommand);
        userInputCommand.put(USER_INPUT_TOP, topCommand);
        userInputCommand.put(USER_INPUT_QUIT, quitCommand);
    }

    public void dispatch() {
        String inputOperation = "Entry one of the oprations: " + String.join(",", userInputCommand.keySet());

        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;
            boolean quit = false;

            while (!quit) {
                System.out.println(inputOperation);
                userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
                if (!userInputCommand.containsKey(userInput)) {
                    System.out.println(NOT_SUPPORTED_OPERATION_MESSAGE);
                } else {
                    commandPerformer.performCommand(userInputCommand.get(userInput));
                    quit = userInput.equals(USER_INPUT_QUIT);
                }
            }
        }
    }
}