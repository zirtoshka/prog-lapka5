package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;

public class HeadCommand extends Command {
    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "print the first elemet of the collecion");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            ConsoleManager.printInfo1(collectionManager.headOfCollection());
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
