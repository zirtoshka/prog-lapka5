package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.IO.ScannerManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.ArgsException;
import org.example.exceptions.BadScriptException;

import java.io.IOException;

public class AddCommand extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public AddCommand(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("add", "add a new element to the collection");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            collectionManager.addToCollection(
                    new StudyGroup(
                            collectionManager.generateId(),
                            scannerManager.askGroupName(),
                            scannerManager.askCoordinates(),
                            collectionManager.getLastInitTime().now(),
                            scannerManager.askStudentCount(),
                            scannerManager.askShouldBeExpelled(),
                            scannerManager.askAverageMark(),
                            scannerManager.askSemesterEnum(),
                            scannerManager.askPerson())
            );
            ConsoleManager.printSuccess("Data added successfully");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (BadScriptException e) {
            ConsoleManager.printError("Bad script");
        }
        return false;
    }
}
