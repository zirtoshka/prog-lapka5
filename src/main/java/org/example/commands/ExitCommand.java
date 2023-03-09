package org.example.commands;

import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "finish program without saving");
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            ConsoleManager.printSuccess("Au revoir");
            System.exit(0);
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        }
        return false;
    }
}
