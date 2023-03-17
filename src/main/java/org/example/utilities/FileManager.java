package org.example.utilities;

import org.example.IO.ConsoleManager;
import org.example.data.StudyGroup;
import org.example.exceptions.NotNullException;
import org.example.parser.ReadYAMLParser;
import org.example.parser.WriteYAMLParses;


import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

public class FileManager {
    private final String path;
    private final String fileName;

    public FileManager(String fileName){
        this.fileName = fileName;
        path = System.getenv("PWD")+"/"+fileName;
    }


    public String getFileName(){
        return fileName;
    }
    public String getPath(){return path;}
    public boolean isFileEmpty() {
        File file = new File(path);
        return file.length() == 0;
    }
    public ArrayDeque<StudyGroup> loadFromFile() {
        ReadYAMLParser yaml = new ReadYAMLParser();
        ArrayDeque<StudyGroup> studyGroupCollection = null;
        try {
            studyGroupCollection = yaml.read(path);
        }catch (IOException e){
            ConsoleManager.printError("No access to load from file");
            System.exit(0);
        }
        return studyGroupCollection;
    }
    public void write(ArrayDeque<StudyGroup> studyGroupCollection){
        try {
            if(fileName.isEmpty()) throw new NotNullException();
            WriteYAMLParses writeYAMLParses = new WriteYAMLParses();
            writeYAMLParses.write(studyGroupCollection,path);
        } catch (NotNullException | IOException e){
            ConsoleManager.printError("No access to write");
        }
        }
    }



