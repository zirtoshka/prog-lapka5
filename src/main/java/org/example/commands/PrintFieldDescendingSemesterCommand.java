package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;

public class PrintFieldDescendingSemesterCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingSemesterCommand(CollectionManager collectionManager) {
        super("print_field_descending_semester_enum", "вывести значения поля semesterEnum всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            int[] count = new int[]{0, 0, 0};
            for (StudyGroup sg : collectionManager.getStudyGroupCollection()
            ) {
                switch (sg.getSemesterEnum().name()) {
                    case "FIRST":
                        count[0] += 1;
                        break;
                    case "SECOND":
                        count[1] += 1;
                        break;
                    case "FIFTH":
                        count[2] += 1;
                        break;
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < count[i]; j++) {
                    switch (i) {
                        case 0:
                            System.out.println("FIRST");
                            break;
                        case 1:
                            System.out.println("SECOND");
                            break;
                        case 2:
                            System.out.println("FIFTH");
                            break;
                    }
                }
            }
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
