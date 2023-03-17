package org.example.commands;

import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.data.StudyGroup;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NullCollectionException;

public class PrintFieldDescendingSemesterCommand extends Command {
    private final CollectionManager collectionManager;
    private final int FIRST = 0;
    private final int SECOND = 1;
    private final int FIFTH = 2;

    public PrintFieldDescendingSemesterCommand(CollectionManager collectionManager) {
        super("print_field_descending_semester_enum", "display the values of the semesterEnum field of all elements in descending order");
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
                        count[FIRST] += 1;
                        break;
                    case "SECOND":
                        count[SECOND] += 1;
                        break;
                    case "FIFTH":
                        count[FIFTH] += 1;
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
