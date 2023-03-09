package org.example.commands;

import org.example.IO.ConsoleManager;
import org.example.exceptions.ArgsException;
import org.example.exceptions.NotNullException;
import org.example.exceptions.WrongNameException;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "use script from file");
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsException();
            ConsoleManager.printSuccess("Script? " + arg + " Yes, something's going on in my head ...");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("I know that silence is golden. But I can't understand a file's name");
        }
        return false;
    }
}
