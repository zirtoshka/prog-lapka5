package org.example;

import org.example.IO.ConsoleManager;
import org.example.IO.ScannerManager;
import org.example.commands.*;
import org.example.utilities.CollectionManager;
import org.example.utilities.CommandManager;
import org.example.utilities.FileManager;
import org.example.utilities.HistoryWriter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String INPUT_COMMAND = "$ ";
    public static final String INPUT_INFO = "> ";

    public static void main(String[] args) throws IOException {
        Scanner userScanner = new Scanner(System.in);
        HistoryWriter historyWriter = new HistoryWriter();
        ScannerManager scannerManager = new ScannerManager(userScanner);
        //FileManager fileManager=new FileManager(new Scanner(System.in).nextLine());
        FileManager fileManager = new FileManager(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        collectionManager.loadFromFile();
        CommandManager commandManager = new CommandManager(
                new HelpCommand(), new InfoCommand(collectionManager), new ShowCommand(collectionManager),
                new AddCommand(collectionManager, scannerManager), new UpdateByIdCommand(collectionManager, scannerManager),
                new RemoveByIdCommand(collectionManager), new ClearCommand(collectionManager), new SaveCommand(collectionManager),
                new ExecuteScriptCommand(), new ExitCommand(), new HeadCommand(collectionManager), new AddIfMaxCommand(collectionManager, scannerManager),
                new HistoryCommand(historyWriter), new FilterContainsNameCommand(collectionManager), new PrintUniqueGroupAdminCommand(collectionManager),
                new PrintFieldDescendingSemesterCommand(collectionManager));
        ConsoleManager consoleManager = new ConsoleManager(commandManager, scannerManager, userScanner, historyWriter);
        consoleManager.toStartMode();


    }
}