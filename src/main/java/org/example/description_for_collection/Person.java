package org.example.description_for_collection;

import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private ColorEye eyeColor; //Поле не может быть null
    private ColorHair hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(){}
    public Person(String name, java.util.Date birthday, ColorEye eyeColor, ColorHair hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setEyeColor(ColorEye eyeColor){
        this.eyeColor=eyeColor;
    }

    public ColorEye getEyeColor() {
        return eyeColor;
    }

    public void setHairColor(ColorHair hairColor) {
        this.hairColor = hairColor;
    }

    public ColorHair getHairColor() {
        return hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
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
