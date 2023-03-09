package org.example.utilities;

import org.example.IO.ConsoleManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.NotNullException;
import org.example.parserYAML.ReadYAMLParser;
import org.example.parserYAML.WriteYAMLParses;


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

    public ArrayDeque<StudyGroup> loadFromFile() {
        ReadYAMLParser yaml = new ReadYAMLParser();
        ArrayDeque<StudyGroup> studyGroupCollection = null;
        try {
            if (path.isEmpty()) throw new NotNullException();
            studyGroupCollection = yaml.read(path);
        } catch (NotNullException e) {
            ConsoleManager.printError("can't be empty");
            System.exit(0);
        }catch (IOException | NullPointerException e){
            ConsoleManager.printError("No access");
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
            ConsoleManager.printError("No access");
            System.exit(0);
        }
        }

    }



