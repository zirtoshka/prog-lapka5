package org.example.description_for_collection;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.example.IO.ConsoleManager;
import org.example.IO.ScannerManager;
import org.example.exceptions.NotNullException;
import org.example.exceptions.WrongNameException;

import java.text.ParseException;
import java.util.Date;
import static org.example.IO.ScannerManager.patternSymbols;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private ColorEye eyeColor; //Поле не может быть null
    private ColorHair hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person() {
    }

    public Person(String name, java.util.Date birthday, ColorEye eyeColor, ColorHair hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public void setName(String name) {

        try {
            if (name == null || name.isEmpty()) throw new NotNullException();
            if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
            this.name = name;
        } catch (NotNullException e) {
            ConsoleManager.printError("Name admin can't be empty, bye");
            System.exit(0);
        } catch (WrongNameException e) {
            ConsoleManager.printError("I can parse only char symbol! (letters, numbers and '_')");
            System.exit(0);
        }
    }

    public String getName() {
        return name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setEyeColor(ColorEye eyeColor) {
        try {
            if (eyeColor == null) throw new NotNullException();
            this.eyeColor = eyeColor;
        } catch (NotNullException e) {
            ConsoleManager.printError("Eye color can't be empty");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            ConsoleManager.printError("Hmm.. I don't know this eye color");
            System.exit(0);
        }
    }

    public ColorEye getEyeColor() {
        return eyeColor;
    }

    public void setHairColor(ColorHair hairColor) {
        try {
            if (hairColor == null){
                this.hairColor=null;
                return;
            }this.hairColor = hairColor;
        } catch (IllegalArgumentException e) {
            ConsoleManager.printError("Hmm.. I don't know this hair color");
            System.exit(0);
        }
    }

    public ColorHair getHairColor() {
        return hairColor;
    }

    public void setNationality(Country nationality) {
        try {
            if (nationality == null){
                this.nationality=null;
                return;
            }this.nationality = nationality;
        } catch (IllegalArgumentException e) {
            ConsoleManager.printError("Hmm.. I don't know this country");
            System.exit(0);
        }
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }
}
