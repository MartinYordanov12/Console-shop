package com.coherentsolutions.store.commands;

public class CommandPerformer {
    public void performCommand(Command command) {
        command.execute();
    }
}