package org.lesson10.additionalTask;


import org.lesson10.additionalTask.exceptions.FileMaxSizeReachedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private final FileLoggerConfiguration fileLoggerConfiguration;

    // Конструктор класу, приймає конфігурацію для налаштувань логування.
    public FileLogger(FileLoggerConfiguration fileLoggerConfiguration) {
        this.fileLoggerConfiguration = fileLoggerConfiguration;
    }

    // Метод для запису повідомлення в файл
    private void fileWriter(String logMessage) {
        // Створюємо об'єкт File для поточного файлу логів
        File logFile = new File(fileLoggerConfiguration.getLogFilePath());

        // Якщо файл перевищує максимальний розмір, перевіряємо і створюємо новий файл
        if (checkSize(logMessage)) {
            logFile = new File(fileLoggerConfiguration.getLogFilePath()); // Оновлений файл після створення нового
        }

        // Записуємо повідомлення в файл в режимі дописування
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logMessage + "\n"); // Записуємо повідомлення
        } catch (IOException e) {
            // Якщо виникла помилка при запису в файл, кидаємо виключення
            throw new RuntimeException("Помилка запису в лoг-файл!", e);
        }
    }

    // Метод для створення нового файлу, коли досягнуто максимального розміру
    public void createFile() {
        // Створюємо ім'я нового файлу з поточною датою та часом для унікальності
        String newFileName = "log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss-SSS")) + ".txt";
        File logFile = new File(fileLoggerConfiguration.getBASE_PATH() + newFileName); // Шлях до нового файлу

        // Створюємо новий файл
        try {
            if (logFile.createNewFile()) {
                System.out.println("Створено новий файл: " + logFile.getPath()); // Якщо файл створено, виводимо повідомлення
                fileLoggerConfiguration.setLogFilePath(logFile.getPath()); // Оновлюємо шлях до файлу в конфігурації
            } else {
                System.out.println("Файл вже існує: " + logFile.getPath()); // Якщо файл уже існує, виводимо повідомлення
            }
        } catch (IOException e) {
            // Якщо виникає помилка при створенні файлу, виводимо повідомлення
            System.out.println("Помилка створення файлу: " + e.getMessage());
        }
    }

    // Метод для перевірки, чи перевищує розмір файлу максимальний ліміт
    private boolean checkSize(String logMessage) {
        // Отримуємо поточний файл логів
        File logFile = new File(fileLoggerConfiguration.getLogFilePath());

        // Виводимо поточний розмір файлу та розмір нового повідомлення
        System.out.println("Поточний розмір файлу: " + logFile.length());
        System.out.println("Розмір нового повідомлення: " + logMessage.length());

        // Перевіряємо, чи перевищує загальний розмір (поточний розмір + розмір повідомлення) максимальний ліміт
        try {
            if (logFile.length() + logMessage.length() > fileLoggerConfiguration.getMaxFileSize()) {

                System.out.println("Розмір перевищено, створюємо новий файл..."); // Виводимо повідомлення, якщо розмір перевищено
                createFile(); // Створюємо новий файл
                throw new FileMaxSizeReachedException("Розмір файлу перевищує максимальний ліміт!"); // Повертаємо true, щоб вказати на необхідність створення нового файлу
            }
        }catch(FileMaxSizeReachedException e){
            System.out.println(e);
            return true;
        }

        return false; // Якщо розмір не перевищено, повертаємо false
    }

    // Метод для запису повідомлення з рівнем INFO
    public void info(String message) {
        // Перевіряємо рівень логування, якщо рівень INFO або DEBUG — записуємо в лог
        if (fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.INFO ||
                fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.DEBUG) {
            fileWriter(fileLoggerConfiguration
                    .getFormat()
                    .replace("HH:mm:ss", LocalDateTime.now().toString()) // Форматуємо повідомлення з часом
                    .replace("LEVEL", fileLoggerConfiguration.getLoggingLevel().toString()) // Вставляємо рівень логування
                    .replace("MESSAGE", message)); // Вставляємо саме повідомлення
        } else {
            fileWriter("-------"); // Якщо рівень не підходить, записуємо дефолтне повідомлення
        }
    }

    // Метод для запису повідомлення з рівнем DEBUG
    public void debug(String message) {
        // Перевіряємо рівень логування, якщо рівень DEBUG — записуємо в лог
        if (fileLoggerConfiguration.getLoggingLevel() == LoggingLevel.DEBUG) {
            fileWriter(fileLoggerConfiguration
                    .getFormat()
                    .replace("HH:mm:ss", LocalDateTime.now().toString()) // Форматуємо повідомлення з часом
                    .replace("LEVEL", fileLoggerConfiguration.getLoggingLevel().toString()) // Вставляємо рівень логування
                    .replace("MESSAGE", message)); // Вставляємо саме повідомлення
        } else {
            fileWriter("-------"); // Якщо рівень не підходить, записуємо дефолтне повідомлення
        }
    }
}