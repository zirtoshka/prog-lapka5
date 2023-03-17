package org.example.IO;

import org.example.Main;
import org.example.description_for_collection.*;
import org.example.exceptions.BadScriptException;
import org.example.exceptions.IncorrectValueException;
import org.example.exceptions.NotNullException;
import org.example.exceptions.WrongNameException;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.example.description_for_collection.Coordinates.MAX_X;
import static org.example.description_for_collection.Coordinates.MIN_Y;

public class ScannerManager {
    private boolean filemode;
    public static final Pattern patternSymbols = Pattern.compile("\\w*");
    public static final Pattern patternNumber = Pattern.compile("(-?)\\d+(.\\d+)?");

    private Scanner scanner;

    public ScannerManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public String askName(String inputTitle, String typeOfName) throws BadScriptException {
        String name;
        while (true) {
            try {
                System.out.println(inputTitle);
                System.out.print(Main.INPUT_INFO);
                name = scanner.nextLine().trim();
                if (filemode) System.out.println(name);
                if (name.equals("")) throw new NotNullException();
                if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError(String.format("%s can't be empty!!!", typeOfName));
                if (filemode) throw new BadScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("I can parse only char symbol! (letters, numbers and '_')");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Name is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return name;
    }

    public String askGroupName() throws BadScriptException {
        return askName("Enter Study Group name", "Study Group name");
    }

    public String askPersonName() throws BadScriptException {
        return askName("Enter Admin name:", "Person name");
    }


    public Double askCoordinatesX() throws BadScriptException {
        String userX;
        Double x;
        while (true) {
            try {
                System.out.println("Enter X coordinate: ");
                System.out.print(Main.INPUT_INFO);
                userX = scanner.nextLine().trim();
                if (userX.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(userX).matches()) throw new WrongNameException();
                x = Double.parseDouble(userX);
                if (x > MAX_X) throw new IncorrectValueException();
                break;
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Double");
                if (filemode) throw new BadScriptException();
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!!");
                if (filemode) throw new BadScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("hmm.. You use symbols not for numbers... why?");
                if (filemode) throw new BadScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("This value has to be less than " + MAX_X);
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("X is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return x;

    }

    public Float askCoordinatesY() throws BadScriptException {
        String userY;
        Float y;
        while (true) {
            try {
                System.out.println("Enter Y coord:");
                System.out.print(Main.INPUT_INFO);
                userY = scanner.nextLine().trim();
                if (userY.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(userY).matches()) throw new WrongNameException();
                y = Float.parseFloat(userY);
                if (y < MIN_Y) throw new IncorrectValueException();
                break;
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Float");
                if (filemode) throw new BadScriptException();
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!!");
                if (filemode) throw new BadScriptException();
            } catch (WrongNameException e) {
                ConsoleManager.printError("hmm.. You use symbols not for numbers... why?");
                if (filemode) throw new BadScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("This value has to be more than " + MIN_Y);
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Y is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }

        }
        return y;
    }

    public Coordinates askCoordinates() throws BadScriptException {
        Double x = askCoordinatesX();
        Float y = askCoordinatesY();
        return new Coordinates(x, y);

    }

    public int askStudentCount() throws BadScriptException, NumberFormatException {
        String userCount;
        int count;
        while (true) {
            try {
                System.out.println("Enter the number of students in a group:");
                System.out.print(Main.INPUT_INFO);
                userCount = scanner.nextLine().trim();
                if (userCount.equals("")) throw new NotNullException();
                count = Integer.parseInt(userCount);
                if (count <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("Are you sure it could be the number of students??");
                if (filemode) throw new BadScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("Are you sure it could be the number of students??");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to int");
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("The number of students is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return count;
    }

    public Integer askShouldBeExpelled() throws BadScriptException, NumberFormatException {
        String userCountExpelled;
        Integer countExpelled;
        while (true) {
            try {
                System.out.println("Enter the number of students to be expelled:");
                System.out.print(Main.INPUT_INFO);
                userCountExpelled = scanner.nextLine().trim();
                if (userCountExpelled.equals("")) throw new NotNullException();
                countExpelled = Integer.parseInt(userCountExpelled);
                if (countExpelled <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Integer");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("The number of students to be expelled is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return countExpelled;
    }

    public double askAverageMark() throws BadScriptException {
        String userMark;
        double countMark;
        while (true) {
            try {
                System.out.println("Enter average mark:");
                System.out.print(Main.INPUT_INFO);
                userMark = scanner.nextLine().trim();
                countMark = Double.parseDouble(userMark);
                if (countMark <= 0) throw new IncorrectValueException();
                break;
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to double");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Average mark is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return countMark;
    }

    public Semester askSemesterEnum() throws BadScriptException {
        String userSemester;
        Semester semester;
        while (true) {
            try {
                System.out.println("Semester list - " + Semester.getList());
                System.out.println("Enter your semester:");
                System.out.print(Main.INPUT_INFO);
                userSemester = scanner.nextLine().trim();
                if (userSemester.equals("")) throw new NotNullException();
                semester = Semester.valueOf(userSemester.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this semester(");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Semester is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return semester;
    }

    public Person askPerson() throws BadScriptException {
        if (askQuestion("Is there an admin?")) {
            return new Person(askPersonName(), askBirthday(), askEyeColor(), askHairColor(), askNationality());
        }
        return null;
    }

    public Date askBirthday() throws BadScriptException {
        String userDate;
        Date date;
        while (true) {
            try {
                System.out.println("You can use formats: 'January 19, 1970', '01/19/1970'");
                System.out.println("Enter your birthday for admin: ");
                System.out.print(Main.INPUT_INFO);
                userDate = scanner.nextLine().trim();
                if (userDate.equals("")) throw new NotNullException();
                date = new Date(userDate);
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("You use a very strange format");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Birthday is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }

        return date;
    }

    public ColorEye askEyeColor() throws BadScriptException {
        String userEyeColor;
        ColorEye colorEye;
        while (true) {
            try {
                System.out.println("Color eye list - " + ColorEye.getList());
                System.out.println("Enter your color eye:");
                System.out.print(Main.INPUT_INFO);
                userEyeColor = scanner.nextLine().trim();
                if (userEyeColor.equals("")) throw new NotNullException();
                colorEye = ColorEye.valueOf(userEyeColor.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this eye color(");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Color is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return colorEye;
    }

    public ColorHair askHairColor() throws BadScriptException {
        String userHairColor;
        ColorHair colorHair;
        while (true) {
            try {
                System.out.println("Color hair list - " + ColorHair.getList());
                System.out.println("Enter your color hair:");
                System.out.print(Main.INPUT_INFO);
                userHairColor = scanner.nextLine().trim();
                if (userHairColor.equals("")) throw new NotNullException();
                colorHair = ColorHair.valueOf(userHairColor.toUpperCase());
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this hair color(");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Color is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return colorHair;
    }

    public Country askNationality() throws BadScriptException {
        String userCountry;
        Country country;
        while (true) {
            try {
                System.out.println("Country list - " + Country.getList());
                System.out.println("Enter your county:");
                System.out.print(Main.INPUT_INFO);
                userCountry = scanner.nextLine().trim();
                if (userCountry.isEmpty()) throw new NotNullException();
                country = Country.valueOf(userCountry.toUpperCase());
                break;
            } catch (NotNullException e) {
                return null;
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this nationality(");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Country is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return country;
    }

    public boolean askQuestion(String question) throws BadScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print(Main.INPUT_INFO);
                answer = scanner.nextLine().trim();
                if (answer.equals("")) throw new NotNullException();
                if (!(answer.equals("+") || answer.equals("-"))) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("I know that silence is golden. But what should I do with it? I only understand + and -");
                if (filemode) throw new BadScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("I believed that you are a smart person and able to distinguish other characters from +/-");
                if (filemode) throw new BadScriptException();
            } catch (NoSuchElementException e) {
                ConsoleManager.printError("Answer is ctrl+D. ok, bye");
                if (filemode) throw new BadScriptException();
                System.exit(0);
            }
        }
        return answer.equals("+");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setFileMode() {
        filemode = true;
    }

    public void setUserMode() {
        filemode = false;
    }
}
