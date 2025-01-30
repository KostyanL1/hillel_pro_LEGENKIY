package org.lesson10.additionalTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoggerConfigurationLoader {
    public FileLoggerConfiguration loadConfig(String configPath){
        try (BufferedReader br = new BufferedReader(new FileReader(configPath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration();
            while ((line = br.readLine()) != null) {
                if (line.contains("FILE: ")) {
                    fileLoggerConfiguration.setBASE_PATH(line.substring(6));
                }
                else if((line.contains("LEVEL: "))){
                    if (line.contains("DEBUG")){
                        fileLoggerConfiguration.setLoggingLevel(LoggingLevel.DEBUG);
                    }
                    else{
                        fileLoggerConfiguration.setLoggingLevel(LoggingLevel.INFO);
                    }
                }
                else if (line.contains("MAX-SIZE: ")){
                    fileLoggerConfiguration.setMaxFileSize(Long.parseLong(line.substring(10)));
                }
                else if (line.contains("FORMAT: ")){
                    fileLoggerConfiguration.setFormat(line.substring(8));
                }
            }
            return fileLoggerConfiguration;
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Файл не знайдено: " + configPath, e);
            } catch (IOException e) {
                throw new RuntimeException("Помилка читання файлу: " + configPath, e);
            }
    }
}
