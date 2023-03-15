package org.example.IO;

import org.example.Main;
import org.example.description_for_collection.*;
import org.example.exceptions.BadScriptException;
import org.example.exceptions.IncorrectValueException;
import org.example.exceptions.NotNullException;
import org.example.exceptions.WrongNameException;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerManager {
    private final Double MAX_X = 576D;
    private final Float MIN_Y = -596F;
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
        String strX;
        //TODO: rework strX
        Double x;
        while (true) {
            try {
                System.out.println("Enter X coord: ");
                System.out.print(Main.INPUT_INFO);
                strX = scanner.nextLine().trim();
                if (strX.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(strX).matches()) throw new WrongNameException();
                x = Double.parseDouble(strX);
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
            }
        }
        return x;

    }

    public Float askCoordinatesY() throws BadScriptException {
        String strY;
        Float y;
        while (true) {
            try {
                System.out.println("Enter Y coord:");
                System.out.print(Main.INPUT_INFO);
                strY = scanner.nextLine().trim();
                if (strY.equals("")) throw new NotNullException();
                if (!patternNumber.matcher(strY).matches()) throw new WrongNameException();
                y = Float.parseFloat(strY);
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
        String askCount;
        int count;
        while (true) {
            try {
                System.out.println("Enter the number of students in a group:");
                System.out.print(Main.INPUT_INFO);
                askCount = scanner.nextLine().trim();
                if (askCount.equals("")) throw new NotNullException();
                count = Integer.parseInt(askCount);
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
            }
        }
        return count;
    }

    public Integer askShouldBeExpelled() throws BadScriptException, NumberFormatException {
        String askCountExpelled;
        Integer countExpelled;
        while (true) {
            try {
                System.out.println("Enter the number of students to be expelled:");
                System.out.print(Main.INPUT_INFO);
                askCountExpelled = scanner.nextLine().trim();
                if (askCountExpelled.equals("")) throw new NotNullException();
                countExpelled = Integer.parseInt(askCountExpelled);
                if (countExpelled <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                return null;
//                ConsoleManager.printError("It can't be empty!!!");
//                if (filemode) throw new BadScriptException();
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to Integer");
                if (filemode) throw new BadScriptException();
            }
        }
        return countExpelled;
    }

    public double askAverageMark() throws BadScriptException {
        String askMark;
        double countMarkd;
        while (true) {
            try {
                System.out.println("Enter average mark:");
                System.out.print(Main.INPUT_INFO);
                askMark = scanner.nextLine().trim();
                countMarkd = Double.parseDouble(askMark);
                if (countMarkd <= 0) throw new IncorrectValueException();
                break;
            } catch (IncorrectValueException e) {
                ConsoleManager.printError("It has to be more than 0");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException e) {
                ConsoleManager.printError("Given String is not parsable to double");
                if (filemode) throw new BadScriptException();
            }
        }
        return countMarkd;
    }

    public Semester askSemesterEnum() throws BadScriptException {
        String askSemester;
        Semester semester;
        while (true) {
            try {
                System.out.println("Semester list - " + Semester.getList());
                System.out.println("Enter your semester:");
                System.out.print(Main.INPUT_INFO);
                askSemester = scanner.nextLine().trim();
                if (askSemester.equals("")) throw new NotNullException();
                semester = Semester.valueOf(askSemester.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this semester(");
                if (filemode) throw new BadScriptException();
            }
        }
        return semester;
    }

    public Person askPerson() throws BadScriptException {
        if(askQuestion("Is admin exist?")){
        return new Person(askPersonName(),askBirthday(),askEyeColor(),askHairColor(),askNationality());}
        return null;
    }

    public Date askBirthday() throws BadScriptException {
        String askDate;
        Date date;
        while (true) {
            try {
                System.out.println("You can use formats: 'January 19, 1970', '01/19/1970'");
                System.out.println("Enter your birthday for admin: ");
                System.out.print(Main.INPUT_INFO);
                askDate = scanner.nextLine().trim();
                if (askDate.equals("")) throw new NotNullException();
                date = new Date(askDate);
                break;
            } catch (NotNullException e) {
//                ConsoleManager.printError("It can't be empty!");
                return null;
//                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("You use a very strange format");
                if (filemode) throw new BadScriptException();
            }}

        return date;
    }

    public ColorEye askEyeColor() throws BadScriptException {
        String askEyeColor;
        ColorEye colorEye;
        while (true) {
            try {
                System.out.println("Color eye list - " + ColorEye.getList());
                System.out.println("Enter your color eye:");
                System.out.print(Main.INPUT_INFO);
                askEyeColor = scanner.nextLine().trim();
                if (askEyeColor.equals("")) throw new NotNullException();
                colorEye = ColorEye.valueOf(askEyeColor.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this eye color(");
                if (filemode) throw new BadScriptException();
            }
        }
        return colorEye;
    }

    public ColorHair askHairColor() throws BadScriptException {
        String askHairColor;
        ColorHair colorHair;
        while (true) {
            try {
                System.out.println("Color hair list - " + ColorHair.getList());
                System.out.println("Enter your color hair:");
                System.out.print(Main.INPUT_INFO);
                askHairColor = scanner.nextLine().trim();
                if (askHairColor.equals("")) throw new NotNullException();
                colorHair = ColorHair.valueOf(askHairColor.toUpperCase());
                break;
            } catch (NotNullException e) {
                return null;
//                ConsoleManager.printError("It can't be empty!!");
//                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this hair color(");
                if (filemode) throw new BadScriptException();
            }
        }
        return colorHair;
    }

    public Country askNationality() throws BadScriptException {
        String askCountry;
        Country country;
        while (true) {
            try {
                System.out.println("Country list - " + Country.getList());
                System.out.println("Enter your county:");
                System.out.print(Main.INPUT_INFO);
                askCountry = scanner.nextLine().trim();
                if (askCountry.isEmpty()) throw new NotNullException();
                country = Country.valueOf(askCountry.toUpperCase());
                break;
            } catch (NotNullException e) {
                return null;
//                ConsoleManager.printError("It can't be empty!!");
//                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this nationality(");
                if (filemode) throw new BadScriptException();
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
