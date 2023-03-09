package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;

public class ClearCommand extends Command {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            collectionManager.clearCollection();
            ConsoleManager.printSuccess("Collection is cleared");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        }
        return false;
    }
}
