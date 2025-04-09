package org.lesson24.app;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<String> logs = new ArrayList<>();
    private static Logger logger;
    private Logger(){
    }

     public static Logger getInstance() {
        if (logger == null){
            logger = new Logger();
        }
         return logger;

     }

     public  void log(Level level, String message){
        this.logs.add(LocalTime.now() + " - " + "[" + level + "] " + "[" + message + "];");
     }

     public  void printLog(){
         this.logs.forEach(System.out::println);
     }

  
}
