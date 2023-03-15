package org.example.utilities;

import org.example.Main;

public class HistoryWriter {
    private String[] history;

    public HistoryWriter() {
        this.history = new String[Main.numberOfCmd];
    }

    public String[] getHistory() {
        return history;
    }

    public void addInHistory(String newCommand) {
        for (int i = 0; i < Main.numberOfCmd-1; i++) {
            history[i] = history[i + 1];
        }
        history[Main.numberOfCmd-1] = newCommand;
    }
}
