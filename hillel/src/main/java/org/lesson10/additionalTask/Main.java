package org.lesson10.additionalTask;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileLoggerConfigurationLoader fileLoggerConfigurationLoader = new  FileLoggerConfigurationLoader();
        FileLogger fileLogger = new FileLogger(fileLoggerConfigurationLoader.loadConfig("files/additionalTasksFiles/config.txt"));
        fileLogger.createFile();
        for (int i = 0; i < 50; i++) {
            fileLogger.info("log info message");
            fileLogger.debug("log debug message");
        }
    }
}
