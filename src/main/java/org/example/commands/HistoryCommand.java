package org.example.commands;

import org.example.utilities.HistoryWriter;
import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;

public class HistoryCommand extends Command {
    private final HistoryWriter historyWriter;

    public HistoryCommand(HistoryWriter historyWriter) {
        super("history", "View last 12 commands");
        this.historyWriter = historyWriter;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgsException();
            String[] history = historyWriter.getHistory();
            System.out.println("Last 12 commands:");
            for (String s : history) {
                if (!(s == null)) {
                    ConsoleManager.printSuccess(s);
                }
            }
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("Usgae: '" + getName() + "'");
        }
        return false;
    }
}

