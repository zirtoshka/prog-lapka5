package org.example.description_for_collection;

public class Coordinates {
    private Double coordinatesX; //Максимальное значение поля: 576, Поле не может быть null
    private Float coordinatesY; //Значение поля должно быть больше -596, Поле не может быть null


    public Coordinates(){}
    public Coordinates(Double x, Float y){
        this.coordinatesX=x;
        this.coordinatesY=y;
    }

    public void setX(Double coordinatesX){
        this.coordinatesX=coordinatesX;
    }

    public Double getCoordinatesX() {
        return coordinatesX;
    }

    public void setY(Float coordinatesY){
        this.coordinatesY=coordinatesY;
    }

    public Float getCoordinatesY() {
        return coordinatesY;
    }

    @Override
    public String toString() {
        return getCoordinatesX()+" "+getCoordinatesY();
    }
}
