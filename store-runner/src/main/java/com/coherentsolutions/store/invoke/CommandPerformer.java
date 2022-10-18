package com.coherentsolutions.store.invoke;

import com.coherentsolutions.store.commands.Command;

public class CommandPerformer {
    public void performCommand(Command command) {
        command.execute();
    }
}