package org.example.data;


import org.example.IO.ConsoleManager;
import org.example.exceptions.IncorrectValueException;
import org.example.exceptions.IncorrectValuesForGroupException;
import org.example.exceptions.NotNullException;
import org.example.exceptions.WrongNameException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.example.IO.ScannerManager.patternSymbols;

public class StudyGroup {
    private Integer id; //Поле не может быть null,
    // Значение поля должно быть больше 0,
    // Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private final Integer defaultId = 1;
    public static final Integer wrongId = -1;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private final String defaultName = "default_name";
    private Coordinates coordinates; //Поле не может быть null
    private final Coordinates defaultCoordinates = new Coordinates();
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final LocalDateTime defaultCreationDate = LocalDateTime.now();
    private int studentsCount; //Значение поля должно быть больше 0
    private int DEFAULT_STUDENT_COUNT = 1;
    private Integer shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null
    private final Integer defaultShouldBeExpelled = null;
    private double averageMark; //Значение поля должно быть больше 0
    private double DEFAULT_AVERAGE_MARK = 1;
    private Semester semesterEnum; //Поле не может быть null
    private final Semester defaultSemesterEnum = Semester.DEFAULT_SEMESTER;
    private Person groupAdmin; //Поле может быть null
    private Person defaultPerson = new Person();

    public StudyGroup() throws IncorrectValuesForGroupException {
        try {
            this.setId(defaultId);
            this.setName(defaultName);
            this.setCoordinates(defaultCoordinates);
            this.setCreationDate(defaultCreationDate);
            this.setStudentsCount(DEFAULT_STUDENT_COUNT);
            this.setShouldBeExpelled(defaultShouldBeExpelled);
            this.setAverageMark(DEFAULT_AVERAGE_MARK);
            this.setSemesterEnum(defaultSemesterEnum);
            this.setGroupAdmin(defaultPerson);
        } catch (IncorrectValuesForGroupException e){
            this.id=wrongId;
        }

    }

    public StudyGroup(Integer id, String name, Coordinates coordinates, java.time.LocalDateTime creationDate, int studentsCount, Integer shouldBeExpelled, double averageMark, Semester semesterEnum, Person groupAdmin) throws IncorrectValuesForGroupException {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public void setName(String name) {
        try {
            if (name == null || name.isEmpty()) throw new NotNullException();
            if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
            this.name = name;
        } catch (NotNullException e) {
            ConsoleManager.printError("Name group can't be empty, so I can't add the group in collection");
            this.name = defaultName;
            this.id = wrongId;

        } catch (WrongNameException e) {
            ConsoleManager.printError("I can parse only char symbol! (letters, numbers and '_'), so I can't add the group in collection");
            this.name = defaultName;
            this.id = wrongId;
        }
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        try {
            if (id == null) throw new NotNullException();
            if (id <= 0) throw new IncorrectValueException();
            this.id = id;
        } catch (NotNullException e) {
            ConsoleManager.printError("ID can't be null, so I can't add the group in collection");
            this.id=wrongId;
        } catch (IncorrectValueException e) {
            ConsoleManager.printError("ID has incorrect value, so I can't add the group in collection");
           this.id=wrongId;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setAverageMark(double averageMark) {
        try {
            if (averageMark <= 0) throw new IncorrectValueException();
            this.averageMark = averageMark;
        } catch (IncorrectValueException e) {
            ConsoleManager.printError("Mark has to be more than 0, so I can't add the group in collection");
            this.averageMark=DEFAULT_AVERAGE_MARK;
            this.id=wrongId;
        } catch (NumberFormatException e) {
            ConsoleManager.printError("Given String is not parsable to double, so I can't add the group in collection");
            System.exit(0);
        }
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setCoordinates(Coordinates coordinates) throws IncorrectValuesForGroupException{
        this.coordinates = new Coordinates();
        this.coordinates.setX(coordinates.getCoordinatesX());
        this.coordinates.setY(coordinates.getCoordinatesY());
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        try {
            this.creationDate = creationDate;
        } catch (RuntimeException e){
            System.out.println(e);
            this.creationDate=defaultCreationDate;
            this.id=wrongId;
        }

    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        try {
            if (semesterEnum == null) throw new NotNullException();
            this.semesterEnum = semesterEnum;
        } catch (NotNullException e) {
            ConsoleManager.printError("Semester can't be empty, so I can't add the group in collection");
            this.semesterEnum=defaultSemesterEnum;
            this.id=wrongId;
        } catch (IllegalArgumentException e) {
            ConsoleManager.printError("Hmm.. I don't know this semester, so I can't add the group in collection");
            this.semesterEnum=semesterEnum;
            this.id=wrongId;
        }

    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        try {
            if (shouldBeExpelled == null) {
                this.shouldBeExpelled = null;
                return;
            }
            if (shouldBeExpelled <= 0) throw new IncorrectValueException();
            this.shouldBeExpelled = shouldBeExpelled;
        } catch (IncorrectValueException e) {
            ConsoleManager.printError("Number of shouldBeExpelled has to be more than 0");
            this.shouldBeExpelled=defaultShouldBeExpelled;
            this.id=wrongId;
        }

    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public void setStudentsCount(int studentsCount) {
        try {
            if (studentsCount <= 0) throw new IncorrectValueException();
            this.studentsCount = studentsCount;
        } catch (IncorrectValueException e) {
            ConsoleManager.printError("StudentCount has to be more than 0, so I can't add the group in collection");
            this.studentsCount=DEFAULT_STUDENT_COUNT;
            this.id=wrongId;
        }

    }

    public int getStudentsCount() {
        return studentsCount;
    }


    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", shouldBeExpelled=" + shouldBeExpelled +
                ", averageMark=" + averageMark +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return studentsCount == that.studentsCount && Double.compare(that.averageMark, averageMark) == 0 && id.equals(that.id) && name.equals(that.name) && coordinates.equals(that.coordinates) && creationDate.equals(that.creationDate) && shouldBeExpelled.equals(that.shouldBeExpelled) && semesterEnum == that.semesterEnum && groupAdmin.equals(that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentsCount, shouldBeExpelled, averageMark, semesterEnum, groupAdmin);
    }
}
