package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;
import org.example.exceptions.StudyGroupNullException;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "remove element by id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            Integer id = Integer.parseInt(arg);
            StudyGroup studyGroup = collectionManager.getById(id);
            if (studyGroup == null) throw new StudyGroupNullException();
            collectionManager.removeById(studyGroup);
            ConsoleManager.printSuccess("Study group was removed");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("what id is? why it is empty?");
        } catch (NumberFormatException e) {
            ConsoleManager.printError("ID must be Integer");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        } catch (StudyGroupNullException e) {
            ConsoleManager.printError("No Study Group with that ID");
        } return false;
    }
}
