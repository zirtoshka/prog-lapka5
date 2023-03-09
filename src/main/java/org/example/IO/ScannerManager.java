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
    private final double MAX_X = 576;
    private final float MIN_Y = -596;
    private boolean filemode;
    private Pattern patternSymbols = Pattern.compile("\\w*");
    private Pattern patternNumber = Pattern.compile("(-?)\\d+(.\\d+)?");

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
        String strCount;
        int count;
        while (true) {
            try {
                System.out.println("Enter the number of students in a group:");
                System.out.print(Main.INPUT_INFO);
                strCount = scanner.nextLine().trim();
                if (strCount.equals("")) throw new NotNullException();
                count = Integer.parseInt(strCount);
                if (count <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                count = 0;
                break;
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
        String strCountExpelled;
        Integer countExpelled;
        while (true) {
            try {
                System.out.println("Enter the number of students to be expelled:");
                System.out.print(Main.INPUT_INFO);
                strCountExpelled = scanner.nextLine().trim();
                if (strCountExpelled.equals("")) throw new NotNullException();
                countExpelled = Integer.parseInt(strCountExpelled);
                if (countExpelled <= 0) throw new IncorrectValueException();
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!!");
                if (filemode) throw new BadScriptException();
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
        String strMark;
        double countMarkd;
        while (true) {
            try {
                System.out.println("Enter average mark:");
                System.out.print(Main.INPUT_INFO);
                strMark = scanner.nextLine().trim();
                countMarkd = Double.parseDouble(strMark);
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
        String strSemester;
        Semester semester;
        while (true) {
            try {
                System.out.println("Semester list - " + Semester.getList());
                System.out.println("Enter your semester:");
                System.out.print(Main.INPUT_INFO);
                strSemester = scanner.nextLine().trim();
                if (strSemester.equals("")) throw new NotNullException();
                semester = Semester.valueOf(strSemester.toUpperCase());
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
        return new Person(askPersonName(), askBirthday(), askEyeColor(), askHairColor(), askNationality());
    }

    public java.util.Date askBirthday() throws BadScriptException {
        String strDate;
        Date date;
        while (true) {
            try {
                System.out.println("You can use formats: 'January 19, 1970', '01/19/1970'");
                System.out.println("Enter your birthday for admin: ");
                System.out.print(Main.INPUT_INFO);
                strDate = scanner.nextLine().trim();
                if (strDate.equals("")) throw new NotNullException();
                date = new Date(strDate);
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("You use a very strange format");
                if (filemode) throw new BadScriptException();
            }
        }
        return date;
    }

    public ColorEye askEyeColor() throws BadScriptException {
        String strEyeColor;
        ColorEye colorEye;
        while (true) {
            try {
                System.out.println("Color eye list - " + ColorEye.getList());
                System.out.println("Enter your color eye:");
                System.out.print(Main.INPUT_INFO);
                strEyeColor = scanner.nextLine().trim();
                if (strEyeColor.equals("")) throw new NotNullException();
                colorEye = ColorEye.valueOf(strEyeColor.toUpperCase());
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
        String strHairColor;
        ColorHair colorHair;
        while (true) {
            try {
                System.out.println("Color hair list - " + ColorHair.getList());
                System.out.println("Enter your color hair:");
                System.out.print(Main.INPUT_INFO);
                strHairColor = scanner.nextLine().trim();
                if (strHairColor.equals("")) throw new NotNullException();
                colorHair = ColorHair.valueOf(strHairColor.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException e) {
                ConsoleManager.printError("I don't know this hair color(");
                if (filemode) throw new BadScriptException();
            }
        }
        return colorHair;
    }

    public Country askNationality() throws BadScriptException {
        String strCountry;
        Country country;
        while (true) {
            try {
                System.out.println("Country list - " + Country.getList());
                System.out.println("Enter your county:");
                System.out.print(Main.INPUT_INFO);
                strCountry = scanner.nextLine().trim();
                if (strCountry.isEmpty()) throw new NotNullException();
                country = Country.valueOf(strCountry.toUpperCase());
                break;
            } catch (NotNullException e) {
                ConsoleManager.printError("It can't be empty!!");
                if (filemode) throw new BadScriptException();
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
