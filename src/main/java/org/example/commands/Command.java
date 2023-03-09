package org.example.commands;

import java.io.IOException;
import java.util.Objects;

public abstract class Command {
    private String name;
    private String discription;

    public Command(String name, String discription) {
        this.name = name;
        this.discription = discription;
    }

    public abstract boolean execute(String arg) throws IOException;


    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return name.equals(command.name) && discription.equals(command.discription);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name = '" + name + "', discription = '" +
                discription + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discription);
    }
}
