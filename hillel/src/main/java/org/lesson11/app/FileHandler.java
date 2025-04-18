package org.lesson11.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.readString;

public class FileHandler {
    public String createFile(String path) {
        File newFile;
        try {
            newFile = new File(path);
            newFile.createNewFile();
        } catch (FileAlreadyExistsException e) {
            return "File already exists!";
        } catch (IOException e) {
            return "Something wrong " + e;
        }
        return "Created " + newFile;
    }

    public String writeToFile(Path path, String content) {
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Recorded in " + path;
    }

    public String readFromFile(String path) {
        try {
            return readString(Path.of(path));
        } catch (IOException e) {
            return "Something wrong " + e.getMessage();
        }
    }
}
