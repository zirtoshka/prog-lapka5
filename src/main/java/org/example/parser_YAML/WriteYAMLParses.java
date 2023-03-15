package org.example.parser_YAML;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.description_for_collection.StudyGroup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

public class WriteYAMLParses {
    public void write(ArrayDeque<StudyGroup> studyGroupArrayDeque, String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(file, studyGroupArrayDeque);
    }
}
