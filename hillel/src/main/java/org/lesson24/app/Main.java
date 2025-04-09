package org.lesson24.app;

public class Main {
    public static void main(String[] args) {


        Logger.getInstance().log(Level.DEBUG, "Lost Data");
        Logger.getInstance().printLog();
        Logger.getInstance().log(Level.INFO, "App started");

        Logger.getInstance().printLog();

    }
}
