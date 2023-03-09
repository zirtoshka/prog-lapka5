package org.example.IO;

import org.example.utilities.CommandManager;
import org.example.utilities.HistoryWriter;
import org.example.Main;
import org.example.exceptions.IncorrectScriptException;
import org.example.exceptions.NoAccessToFileException;
import org.example.exceptions.ScriptRecurentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager {
    private CommandManager commandManager;
    private ScannerManager scannerManager;
    private final Scanner scanner;
    private final HistoryWriter historyWriter;
    private final List<String> script = new LinkedList<>();

    public ConsoleManager(CommandManager commandManager, ScannerManager scannerManager, Scanner scanner, HistoryWriter historyWriter) {
        this.commandManager = commandManager;
        this.scannerManager = scannerManager;
        this.scanner = scanner;
        this.historyWriter = historyWriter;
    }

    public static void printInfo(Object message) {
        System.out.println("\u001B[35m" + message + "\u001B[0m");
    }

    public static void printInfo1(Object message) {
        System.out.println("\u001B[45m" + message + "\u001B[0m");
    }

    public static void printForNothing(Object message) {
        System.out.println("\u001B[36m" + message + "\u001B[0m");
    }

    public static void printSuccess(Object message) {
        System.out.println("\u001B[42m" + message + "\u001B[0m");
    }

    public static void printError(Object message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }


    public int launchCmd(String[] userCmd) throws IOException {
        String cmd = userCmd[0];
        String arg = userCmd[1];
        switch (cmd) {
            case "help":
                if (commandManager.help(arg)) {
                    historyWriter.addInHistory("help");
                    return 1;
                }
                break;
            case "":
                ConsoleManager.printError("Well... This is an empty line... Maybe you want to ask something?");
                break;
            case "info":
                if (commandManager.info(arg)) {
                    historyWriter.addInHistory("info");
                    return 1;
                }
                break;
            case "show":
                if (commandManager.show(arg)) {
                    historyWriter.addInHistory("show");
                    return 1;
                }
                break;
            case "add":
                if (commandManager.add(arg)) {
                    historyWriter.addInHistory("add");
                    return 1;
                }
                break;
            case "update_by_id":
                if (commandManager.updateById(arg)) {
                    historyWriter.addInHistory("update_by_id");
                    return 1;
                }
                break;
            case "remove_by_id":
                if (commandManager.removeById(arg)) {
                    historyWriter.addInHistory("remove_by_id");
                    return 1;
                }
                break;
            case "clear":
                if (commandManager.clear(arg)) {
                    historyWriter.addInHistory("clear");
                    return 1;
                }
                break;
            case "save":
                if (commandManager.save(arg)) {
                    historyWriter.addInHistory("save");
                    return 1;
                }
                break;
            case "execute_script":
                if (commandManager.executeScript(arg)) {
                    historyWriter.addInHistory("execute_script");
                    return scriptMode(arg);
                }
            case "exit":
                if (commandManager.exit(arg)) {
                    historyWriter.addInHistory("exit");
                    return 1;
                }
                break;
            case "head":
                if (commandManager.head(arg)) {
                    historyWriter.addInHistory("head");
                    return 1;
                }
                break;
            case "add_if_max":
                if (commandManager.addIfMax(arg)) {
                    historyWriter.addInHistory("add_if_max");
                    return 1;
                }
                break;
            case "history":
                if (commandManager.history(arg)) {
                    historyWriter.addInHistory("history");
                    return 1;
                }
                break;
            case "filter_contains_name":
                if (commandManager.filterContainsName(arg)) {
                    historyWriter.addInHistory("filter_contains_name");
                    return 1;
                }
                break;
            case "print_unique_group_admin":
                if (commandManager.printUniqueAdmin(arg)) {
                    historyWriter.addInHistory("print_unique_group_admin");
                    return 1;
                }
                break;
            case "print_field_descending_semester_enum":
                if (commandManager.printFieldDescendingSemester(arg)) {
                    historyWriter.addInHistory("print_field_descending_semester_enum");
                    return 1;
                }
                break;
            default:
                historyWriter.addInHistory("I don't know this command:((");
                ConsoleManager.printError("No such command as in list");
                break;
        }
        return 0;
    }

    public void toStartMode() throws IOException {
        String[] userCmd = {"", ""};
        int cmdStatus;

        do {
            System.out.print(Main.INPUT_COMMAND);
            userCmd = (scanner.nextLine().trim() + " ").split(" ", 2);
            userCmd[1] = userCmd[1].trim();
            cmdStatus = launchCmd(userCmd);
        } while (cmdStatus != 2);


    }

    public int scriptMode(String arg) throws IOException {
        String path;
        String[] userCmd = {"", ""};
        int cmdStatus;
        script.add(arg);
        try {
            path = System.getenv("PWD") + "/" + arg;
            File file = new File(arg);
            if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
            Scanner scriptScanner = new Scanner(file);
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = scannerManager.getScanner();
            scannerManager.setScanner(scriptScanner);
            scannerManager.setFileMode();
            do {
                userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCmd[1] = userCmd[1].trim();
                while (scriptScanner.hasNextLine() && userCmd[0].isEmpty()) {
                    userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCmd[1] = userCmd[1].trim();
                }
                System.out.println(Main.INPUT_COMMAND + String.join(" ", userCmd));
                if (userCmd[0].equals("execute_script")) {
                    for (String scri : script) {
                        if (userCmd[1].equals(scri)) throw new ScriptRecurentException();
                    }
                }
                cmdStatus = launchCmd(userCmd);
            } while (cmdStatus == 1 && scriptScanner.hasNextLine());
            scannerManager.setScanner(tmpScanner);
            scannerManager.setUserMode();
            if (cmdStatus == 0 && !userCmd[0].equals("execute_script") && userCmd[1].isEmpty())
                throw new IncorrectScriptException();
            return cmdStatus;
        } catch (NoAccessToFileException e) {
            ConsoleManager.printError("No rules");
        } catch (NoSuchElementException e) {
            ConsoleManager.printError("I can't do anything with empty file");
        } catch (FileNotFoundException e) {
            ConsoleManager.printError("No such file with script");
        } catch (ScriptRecurentException e) {
            ConsoleManager.printError("Recurrent is cool, but I don't know how to use it");
        } catch (IncorrectScriptException e) {
            ConsoleManager.printError("Script is incorrect");
        } finally {
            script.remove(script.size() - 1);
        }
        return 1;
    }
}


























