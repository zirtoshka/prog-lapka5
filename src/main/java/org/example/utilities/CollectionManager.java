package org.example.utilities;

import org.example.IO.ConsoleManager;
import org.example.description_for_collection.StudyGroup;
import org.example.exceptions.NotNullException;
import org.example.exceptions.NullCollectionException;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class CollectionManager {
    private ArrayDeque<StudyGroup> studyGroupCollection;
    private Set<Integer> idSet = new HashSet<>();

    private Integer newId = 1;
    private final int Size_Empty=0;
    private final String Empty_Collection= "empty";
    private final FileManager fileManager;

    private java.time.LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;


    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
    }
    public void createCollection(){
        this.studyGroupCollection=new ArrayDeque<StudyGroup>();
    }
    public void loadFromFile() {
        this.studyGroupCollection = fileManager.loadFromFile();
        for (StudyGroup stg : studyGroupCollection) {
            idSet.add(stg.getId());
        }
    }

    public void writeToFile() {
        fileManager.write(this.studyGroupCollection);
    }

    public ArrayDeque<StudyGroup> getStudyGroupCollection() {
        return studyGroupCollection;
    }

    public Integer generateId() {
        while (!idSet.add(newId)) {
            newId++;
        }
        return newId;
    }

    public void addToCollection(StudyGroup studyGroupFromUser) {
        studyGroupCollection.add(studyGroupFromUser);
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType() {
        try {
            if(studyGroupCollection.isEmpty()) throw new NullCollectionException();
            return studyGroupCollection.getClass().getName();
        } catch (NullCollectionException e){
            ConsoleManager.printError("Collection is empty");
        }
        return Empty_Collection;
    }

    public int collectionSize() {
        try {
            if(studyGroupCollection ==null) throw new NullCollectionException();
            return studyGroupCollection.size();
        } catch (NullCollectionException e){
//            ConsoleManager.printError("Collection is empty");
            return Size_Empty;
        }

    }

    public void clearCollection() {
        try {
            if(studyGroupCollection ==null) throw new NullCollectionException();
            studyGroupCollection.clear();
        } catch (NullCollectionException e){
        }

    }

    public void saveCollection() {
        this.writeToFile();
        lastSaveTime = LocalDateTime.now();
    }

    public String headOfCollection() {
        try {
            if(studyGroupCollection.isEmpty()) throw new NullCollectionException();
            return studyGroupCollection.getFirst().toString();
        }catch (NullCollectionException e){
            ConsoleManager.printError("Collection is empty");
        }
        return Empty_Collection;
    }

    public StudyGroup getById(Integer id) {
//        try {
//            if(studyGroupCollection.isEmpty()) throw new NullCollectionException();
//        }
        for (StudyGroup studyGroup : studyGroupCollection) {
            if (studyGroup.getId().equals(id)) {
                return studyGroup;
            }
        }
        return null;
    }

    public void removeFromCollection(StudyGroup studyGroup) {
        studyGroupCollection.remove(studyGroup);
    }

    public void removeById(StudyGroup studyGroup) {
        idSet.remove(studyGroup.getId());
        studyGroupCollection.remove(studyGroup);
    }

    public int getMaxNumberInGroup() {
        int res = -1;
        for (StudyGroup group : studyGroupCollection) {
            if (group.getStudentsCount() > res) {
                res = group.getStudentsCount();
            }
        }
        return res;
    }

    @Override
    public String toString() {
        if (studyGroupCollection.isEmpty()) {
            return "Collection is empty(((";
        }
        StringBuilder info = new StringBuilder();
        for (StudyGroup studyGroup : studyGroupCollection) {
            info.append(studyGroup.toString());
            if (studyGroup != studyGroupCollection.getLast()) {
                info.append("\n\n");
            }
        }
        return info.toString();
    }


}
