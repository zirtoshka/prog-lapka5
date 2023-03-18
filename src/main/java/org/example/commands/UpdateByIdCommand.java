package org.example.commands;

import org.example.exceptions.*;
import org.example.utilities.CollectionManager;
import org.example.IO.ConsoleManager;
import org.example.IO.ScannerManager;
import org.example.data.Coordinates;
import org.example.data.Person;
import org.example.data.Semester;
import org.example.data.StudyGroup;

import java.time.LocalDateTime;

public class UpdateByIdCommand extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public UpdateByIdCommand(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("update_by_id <id>", "update element by id");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsException();
            if (collectionManager.collectionSize() == 0) throw new NullCollectionException();
            Integer id = Integer.parseInt(arg);
            StudyGroup studyGroup = collectionManager.getById(id);
            if (studyGroup == null) throw new StudyGroupNullException();
            String name = studyGroup.getName();
            Coordinates coordinates = studyGroup.getCoordinates();
            LocalDateTime creationDate = studyGroup.getCreationDate();
            int studentsCount = studyGroup.getStudentsCount();
            Integer shouldBeExpelled = studyGroup.getShouldBeExpelled();
            double averageMark = studyGroup.getAverageMark();
            Semester semesterEnum = studyGroup.getSemesterEnum();
            Person groupAdmin = studyGroup.getGroupAdmin();

            collectionManager.removeFromCollection(studyGroup);//метод ничего не делает с множеством поэтом id можно присвоить тот же и все будет норм
            //id генерируются, поэтому будет колллекция, с новым id
            if (scannerManager.askQuestion("Change study group name?")) {
                name = scannerManager.askGroupName();
            }
            if (scannerManager.askQuestion("Change study group coordinates?")) {
                coordinates = scannerManager.askCoordinates();
            }
            if (scannerManager.askQuestion("Change the number of students in a group??")) {
                studentsCount = scannerManager.askStudentCount();
            }
            if (scannerManager.askQuestion("Change the number of students to be expelled??")) {
                shouldBeExpelled = scannerManager.askShouldBeExpelled();
            }
            if (scannerManager.askQuestion("Change study group average mark?")) {
                averageMark = scannerManager.askAverageMark();
            }
            if (scannerManager.askQuestion("Change study group semester?")) {
                semesterEnum = scannerManager.askSemesterEnum();
            }
            if (scannerManager.askQuestion("Change study group admin?")) {
                groupAdmin = scannerManager.askPerson();
            }

            collectionManager.addToCollection(new StudyGroup(id, name, coordinates, creationDate, studentsCount,
                    shouldBeExpelled, averageMark, semesterEnum, groupAdmin));
            ConsoleManager.printSuccess("Info was successfully updated");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usage: '" + getName() + "'");
        } catch (NullCollectionException e) {
            ConsoleManager.printError("Collection is empty");
        } catch (StudyGroupNullException e) {
            ConsoleManager.printError("No such Study Group with that ID");
        }catch (IncorrectScriptException | IncorrectValuesForGroupException e){
            ConsoleManager.printError("Script is in correct in cmd update_by_id");
        }
        return false;
    }
}
