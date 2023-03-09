package org.example.description_for_collection;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudyGroup {
    private Integer id; //Поле не может быть null,
    // Значение поля должно быть больше 0,
    // Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private Integer shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null
    private double averageMark; //Значение поля должно быть больше 0
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null
    public StudyGroup(){}

    public StudyGroup(Integer id, String name, Coordinates coordinates, java.time.LocalDateTime creationDate, int studentsCount, Integer shouldBeExpelled, double averageMark, Semester semesterEnum, Person groupAdmin) {
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
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    //дата должна сама генерироваться
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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
        this.semesterEnum = semesterEnum;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

//    @Override
//    public String toString() {
//        return getId()+"\n"+getName()+"\n"+getCoordinates()+"\n"+getCreationDate()+"\n"+getStudentsCount()+"\n"+getShouldBeExpelled()+"\n"+getAverageMark()+"\n"+semesterEnum+"\n"+getGroupAdmin();
//    }

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
