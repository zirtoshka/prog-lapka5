package org.example.commands;

import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;
import org.example.exceptions.IncorrectScriptException;

import java.io.IOException;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "display help on available commands");
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        }
        return false;
    }
}
