package org.lesson13.additionalTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bar bar = new Bar();

        for ( int i = 1; i <= 10; i ++){
            Thread.sleep(200);
             Thread thread = new Thread(new Client("№" + i, bar)) ;
             thread.start();
        }

    }
}
