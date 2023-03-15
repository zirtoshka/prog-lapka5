package org.example.parser_YAML;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.description_for_collection.StudyGroup;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.*;
import java.util.ArrayDeque;

public class ReadYAMLParser {

    public ArrayDeque<StudyGroup> read(String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JavaTimeModule());
        ArrayDeque<StudyGroup> collectionFromFile = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(ArrayDeque.class, StudyGroup.class));
        return collectionFromFile;
    }
}

