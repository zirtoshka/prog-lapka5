package org.example.description_for_collection;

import org.example.IO.ConsoleManager;
import org.example.exceptions.IncorrectValueException;
import org.example.exceptions.NotNullException;

import java.io.IOException;

public class Coordinates {

    private Double coordinatesX; //Максимальное значение поля: 576, Поле не может быть null
    private static final Double MAX_X = 576D;
    private static final Float MIN_Y = -596F;
    private Float coordinatesY; //Значение поля должно быть больше -596, Поле не может быть null


    public Coordinates() {
    }

    public Coordinates(Double x, Float y)  {
        this.setX(x);
        this.setY(y);
        }

    public void setX(Double coordinatesX) {
        try {
            System.out.println("7");
            if (coordinatesX == null) throw new NotNullException();
            if (coordinatesX > MAX_X) throw new IncorrectValueException();
            this.coordinatesX = coordinatesX;
        } catch (IncorrectValueException e ) {
            ConsoleManager.printError("This value (x) has to be less than " + MAX_X);
            System.exit(0);
        } catch (NotNullException e) {
            ConsoleManager.printError("X can't be null");
            System.exit(0);
        }
    }

    public Double getCoordinatesX() {
        return coordinatesX;
    }

    public void setY(Float coordinatesY) {
        try {
            System.out.println("8");
            if (coordinatesY == null) throw new NotNullException();
            if (coordinatesY < MIN_Y) throw new IncorrectValueException();
            this.coordinatesY = coordinatesY;
        } catch (IncorrectValueException e) {
            ConsoleManager.printError("This value (y) has to be more than " + MIN_Y);
            System.exit(0);
        } catch (NotNullException e) {
            ConsoleManager.printError("Y can't be null");
            System.exit(0);
        }
    }

    public Float getCoordinatesY() {
        return coordinatesY;
    }

    @Override
    public String toString() {
        return getCoordinatesX() + " " + getCoordinatesY();
    }
}
