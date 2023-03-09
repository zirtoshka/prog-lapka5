package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;

public class FilterContainsNameCommand extends Command {
    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super("filter_contains_name <name>", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            String name = arg;
            boolean isDone = false;
            for (StudyGroup sg : collectionManager.getStudyGroupCollection()
            ) {
                if (sg.getName().contains(name)) {
                    ConsoleManager.printInfo1(sg.getName());
                    isDone = true;
                }
            }
            if (!isDone) {
                ConsoleManager.printForNothing("I can't find smth with " + name);
            }
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("What do I need to find???");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
