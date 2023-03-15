package org.example.IO;

import org.example.commands.CommandEnum;
import org.example.commands.FilterContainsNameCommand;
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
    private final CommandManager commandManager;
    private final ScannerManager scannerManager;
    private final Scanner scanner;

    private final HistoryWriter historyWriter;
    private final List<String> script = new LinkedList<>();
    private final int NAME_CMD = 0;
    private final int ARG_CMD = 1;
    private final int SUCCESSFUL_EXECUTION = 1;
    private final int NOT_SUCCESSFUL_EXECUTION = 0;


    private final String ADD = "add";
    private final String ADD_IF_MAX = "add_if_max";
    private final String CLEAR = "clear";
    private final String EXECUTE_SCRIPT = "execute_script";
    private final String EXIT = "exit";
    private final String FILTER_CONTAINS_NAME = "filter_contains_name";
    private final String HEAD = "head";
    private final String HELP = "help";
    private final String HISTORY = "history";
    private final String INFO = "info";
    private final String PRINT_FIELD_DESCENDING_SEMESTER = "print_field_descending_semester_enum";
    private final String PRINT_UNIQUE_GROUP_ADMIN = "print_unique_group_admin";
    private final String REMOVE_BY_ID = "remove_by_id";
    private final String SAVE = "save";
    private final String SHOW = "show";
    private final String UPDATE_BY_ID = "update_by_id";


    public ConsoleManager(CommandManager commandManager, ScannerManager scannerManager, Scanner scanner, HistoryWriter historyWriter) {
        this.commandManager = commandManager;
        this.scannerManager = scannerManager;
        this.scanner = scanner;
        this.historyWriter = historyWriter;
    }

    public static void printInfoPurple(Object message) {
        System.out.println("\u001B[35m" + message + "\u001B[0m");
    }

    public static void printInfoPurpleBackground(Object message) {
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
        String cmd = userCmd[NAME_CMD];
        String arg = userCmd[ARG_CMD];
        switch (cmd) {
            case HELP:
                if (commandManager.help(arg)) {
                    historyWriter.addInHistory(HELP);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case "":
                ConsoleManager.printError("Well... This is an empty line... Maybe you want to ask something?");
                break;
            case INFO:
                if (commandManager.info(arg)) {
                    historyWriter.addInHistory(INFO);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case SHOW:
                if (commandManager.show(arg)) {
                    historyWriter.addInHistory(SHOW);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case ADD:
                if (commandManager.add(arg)) {
                    historyWriter.addInHistory(ADD);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case UPDATE_BY_ID:
                if (commandManager.updateById(arg)) {
                    historyWriter.addInHistory(UPDATE_BY_ID);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case REMOVE_BY_ID:
                if (commandManager.removeById(arg)) {
                    historyWriter.addInHistory(REMOVE_BY_ID);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case CLEAR:
                if (commandManager.clear(arg)) {
                    historyWriter.addInHistory(CLEAR);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case SAVE:
                if (commandManager.save(arg)) {
                    historyWriter.addInHistory(SAVE);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case EXECUTE_SCRIPT:
                if (commandManager.executeScript(arg)) {
                    historyWriter.addInHistory(EXECUTE_SCRIPT);
                    return scriptMode(arg);
                }
            case EXIT:
                if (commandManager.exit(arg)) {
                    historyWriter.addInHistory(EXIT);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case HEAD:
                if (commandManager.head(arg)) {
                    historyWriter.addInHistory(HEAD);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case ADD_IF_MAX:
                if (commandManager.addIfMax(arg)) {
                    historyWriter.addInHistory(ADD_IF_MAX);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case HISTORY:
                if (commandManager.history(arg)) {
                    historyWriter.addInHistory(HISTORY);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case FILTER_CONTAINS_NAME:
                if (commandManager.filterContainsName(arg)) {
                    historyWriter.addInHistory(FILTER_CONTAINS_NAME);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case PRINT_UNIQUE_GROUP_ADMIN:
                if (commandManager.printUniqueAdmin(arg)) {
                    historyWriter.addInHistory(PRINT_UNIQUE_GROUP_ADMIN);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case PRINT_FIELD_DESCENDING_SEMESTER:
                if (commandManager.printFieldDescendingSemester(arg)) {
                    historyWriter.addInHistory(PRINT_FIELD_DESCENDING_SEMESTER);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            default:
                historyWriter.addInHistory("I don't know this command:((");
                ConsoleManager.printError("No such command as in list");
                break;
        }
        return NOT_SUCCESSFUL_EXECUTION;
    }


    public void toStartMode() throws IOException {
        String[] userCmd = {"", ""};
        int cmdStatus;

        do {
            System.out.print(Main.INPUT_COMMAND);
            userCmd = (scanner.nextLine().trim() + " ").split(" ", 2);
            userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
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
            File file = new File(path);
            if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
            Scanner scriptScanner = new Scanner(file);
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = scannerManager.getScanner();
            scannerManager.setScanner(scriptScanner);
            scannerManager.setFileMode();
            do {
                userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
                while (scriptScanner.hasNextLine() && userCmd[NAME_CMD].isEmpty()) {
                    userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
                }
                System.out.println(Main.INPUT_COMMAND + String.join(" ", userCmd));
                if (userCmd[NAME_CMD].equals("execute_script")) {
                    for (String scri : script) {
                        if (userCmd[ARG_CMD].equals(scri)) throw new ScriptRecurentException();
                    }
                }
                cmdStatus = launchCmd(userCmd);
            } while (cmdStatus == SUCCESSFUL_EXECUTION && scriptScanner.hasNextLine());
            scannerManager.setScanner(tmpScanner);
            scannerManager.setUserMode();
            if (cmdStatus == NOT_SUCCESSFUL_EXECUTION && !userCmd[NAME_CMD].equals("execute_script") && userCmd[ARG_CMD].isEmpty())
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
        return SUCCESSFUL_EXECUTION;
    }
}


























