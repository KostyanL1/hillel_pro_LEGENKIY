package org.lesson10.additionalTask;


import org.lesson10.additionalTask.exceptions.FileMaxSizeReachedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private final FileLoggerConfiguration fileLoggerConfiguration;

    public FileLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        this.fileLoggerConfiguration = fileLoggerConfiguration;
    }

    private void fileWriter(String logMessage) {
        File logFile = new File(fileLoggerConfiguration.getLogFilePath());

        if (checkSize(logMessage)) {
            logFile = new File(fileLoggerConfiguration.getLogFilePath());
        }

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису в лoг-файл!", e);
        }
    }


    public void createFile() {
        String newFileName = "log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss-SSS")) + ".txt";
        File logFile = new File(fileLoggerConfiguration.getBASE_PATH() + newFileName);
        try {
            if (logFile.createNewFile()) {
                System.out.println("Створено новий файл: " + logFile.getPath());
                fileLoggerConfiguration.setLogFilePath(logFile.getPath());
            } else {
                System.out.println("Файл вже існує: " + logFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
        }
    }



    private boolean checkSize(String logMessage) {
        File logFile = new File(fileLoggerConfiguration.getLogFilePath());
        System.out.println("Поточний розмір файлу: " + logFile.length());
        System.out.println("Розмір нового повідомлення: " + logMessage.length());

        try {
            if (logFile.length() + logMessage.length() > fileLoggerConfiguration.getMaxFileSize()) {

                System.out.println("Розмір перевищено, створюємо новий файл...");
                createFile();
                throw new FileMaxSizeReachedException("Розмір файлу перевищує максимальний ліміт!");
            }
        }catch(FileMaxSizeReachedException e){
            System.out.println(e);
            return true;
        }

        return false;
    }


    public void info(String message) {
        if (fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.INFO ||
                fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.DEBUG) {
            fileWriter(fileLoggerConfiguration
                    .getFormat()
                    .replace("HH:mm:ss", LocalDateTime.now().toString())
                    .replace("LEVEL", fileLoggerConfiguration.getLoggingLevel().toString())
                    .replace("MESSAGE", message));
        } else {
            fileWriter("-------");
        }
    }

    public void debug(String message) {

        if (fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.DEBUG) {
            fileWriter(fileLoggerConfiguration
                    .getFormat()
                    .replace("HH:mm:ss", LocalDateTime.now().toString())
                    .replace("LEVEL", fileLoggerConfiguration.getLoggingLevel().toString())
                    .replace("MESSAGE", message));
        } else {
            fileWriter("-------");
        }
    }
}