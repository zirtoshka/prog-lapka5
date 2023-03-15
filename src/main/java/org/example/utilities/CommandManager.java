package org.example.utilities;

import org.example.IO.ConsoleManager;
import org.example.commands.Command;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CommandManager {
    private final List<Command> commands = new LinkedList<>();
    private final Command helpCmd;
    private final Command infoCmd;
    private final Command showCmd;
    private final Command addCmd;
    private final Command updateByIdCmd;
    private final Command removeByIdCmd;
    private final Command clearCmd;
    private final Command saveCmd;
    private final Command executeScriptCmd;
    private final Command exitCmd;
    private final Command headCmd;
    private final Command addIfMaxCmd;
    private final Command historyCmd;
    private final Command filterContainsNameCmd;
    private final Command printUniqueAdminCmd;
    private final Command printFieldDescendingSemesterCmd;


    public CommandManager(Command helpCmd, Command infoCmd, Command showCmd,
                          Command addCmd, Command updateByIdCmd, Command removeByIdCmd,
                          Command clearCmd, Command saveCmd, Command executeScriptCmd, Command exitCmd, Command headCmd,
                          Command addIfMaxCmd, Command historyCmd, Command filterContainsNameCmd, Command printUniqueAdminCmd,
                          Command printFieldDescendingSemesterCmd) {
        this.helpCmd = helpCmd;
        this.infoCmd = infoCmd;
        this.showCmd = showCmd;
        this.addCmd = addCmd;
        this.updateByIdCmd = updateByIdCmd;
        this.removeByIdCmd = removeByIdCmd;
        this.clearCmd = clearCmd;
        this.saveCmd = saveCmd;
        this.executeScriptCmd = executeScriptCmd;
        this.exitCmd = exitCmd;
        this.addIfMaxCmd = addIfMaxCmd;
        this.historyCmd = historyCmd;
        this.headCmd = headCmd;
        this.filterContainsNameCmd = filterContainsNameCmd;
        this.printFieldDescendingSemesterCmd = printFieldDescendingSemesterCmd;
        this.printUniqueAdminCmd = printUniqueAdminCmd;

        commands.add(helpCmd);
        commands.add(infoCmd);
        commands.add(showCmd);
        commands.add(addCmd);
        commands.add(updateByIdCmd);
        commands.add(removeByIdCmd);
        commands.add(clearCmd);
        commands.add(saveCmd);
        commands.add(executeScriptCmd);
        commands.add(exitCmd);
        commands.add(addIfMaxCmd);
        commands.add(historyCmd);
        commands.add(filterContainsNameCmd);
        commands.add(printFieldDescendingSemesterCmd);
        commands.add(printUniqueAdminCmd);
    }

    public boolean info(String arg) throws IOException {
        return infoCmd.execute(arg);
    }

    public boolean show(String arg) throws IOException {
        return showCmd.execute(arg);
    }

    public boolean help(String arg) throws IOException {
        if (helpCmd.execute(arg)) {
            for (Command cmd : commands) {
                ConsoleManager.printInfoPurpleBackground("Command name - " + cmd.getName() + ". Command's description: " + cmd.getDescription());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean add(String arg) throws IOException {
        return addCmd.execute(arg);
    }

    public boolean updateById(String arg) throws IOException {
        return updateByIdCmd.execute(arg);
    }

    public boolean removeById(String arg) throws IOException {
        return removeByIdCmd.execute(arg);
    }

    public boolean clear(String arg) throws IOException {
        return clearCmd.execute(arg);
    }

    public boolean save(String arg) throws IOException {
        return saveCmd.execute(arg);
    }

    public boolean executeScript(String arg) throws IOException {
        return executeScriptCmd.execute(arg);
    }

    public boolean exit(String arg) throws IOException {
        return exitCmd.execute(arg);
    }

    public boolean head(String arg) throws IOException {
        return headCmd.execute(arg);
    }

    public boolean addIfMax(String arg) throws IOException {
        return addIfMaxCmd.execute(arg);
    }

    public boolean history(String arg) throws IOException {
        return historyCmd.execute(arg);
    }

    public boolean filterContainsName(String arg) throws IOException {
        return filterContainsNameCmd.execute(arg);
    }

    public boolean printUniqueAdmin(String arg) throws IOException {
        return printUniqueAdminCmd.execute(arg);
    }

    public boolean printFieldDescendingSemester(String arg) throws IOException {
        return printFieldDescendingSemesterCmd.execute(arg);
    }

}
