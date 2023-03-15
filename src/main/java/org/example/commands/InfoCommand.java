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
            ConsoleManager.printInfoPurple("Collection info:");
            ConsoleManager.printInfoPurple(" Type: " + collectionManager.collectionType());
            ConsoleManager.printInfoPurple(" Quantity: " + collectionManager.collectionSize());
            ConsoleManager.printInfoPurple(" Last save: " + lastSaveTimeStr);
            ConsoleManager.printInfoPurple(" Last enter: " + lastInitTimeStr);
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
