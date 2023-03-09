package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;

import java.time.LocalDateTime;

public class InfoCommand extends Command {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "print information about the collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeStr = (lastInitTime == null) ? "No command in this session" :
                    lastInitTime.toString();
            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeStr = (lastSaveTime == null) ? "No saves in this session" :
                    lastSaveTime.toString();
            ConsoleManager.printInfo("Collection info:");
            ConsoleManager.printInfo(" Type: " + collectionManager.collectionType());
            ConsoleManager.printInfo(" Quantity: " + collectionManager.collectionSize());
            ConsoleManager.printInfo(" Last save: " + lastSaveTimeStr);
            ConsoleManager.printInfo(" Last enter: " + lastInitTimeStr);
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
