package org.example.util;

import org.example.Content.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class fileUtil {

    public static String getFile(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
            System.out.println("File created successfully!");
        } else {
            return file.toString();
        }
        return fileName;
    }

    public static void saveAll(List<String> lines) throws IOException {
        Path fileName = Path.of(getFile("taskRecord.txt"));
        Files.write(fileName, lines);
    }

    public static void writeToFile(String line) throws IOException {

        Path fileName = Path.of(getFile("taskRecord.txt"));

        try {
            if(Files.exists(fileName)){
                if (!line.endsWith(System.lineSeparator())) {
                    line = line + System.lineSeparator();
                }
                Files.write(fileName, line.getBytes(), StandardOpenOption.APPEND);
            }

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public static List<String> readFromFile(Path fileName) throws IOException {
        try {
            if (fileName != null && Files.exists(fileName)) {
                return Files.readAllLines(fileName);
            } else {
                String defaultFile = getFile("taskRecord.txt");
                return Files.readAllLines(Path.of(defaultFile));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }

    public static List<String> searchLine(String id) throws IOException {

        List<String> lines = readFromFile(Path.of("taskRecord.txt"));

        for(String line : lines){
            if(line.contains(id)){
                System.out.println("Task found");
                return List.of(line);
            }
        }
        return null;
    }


}
