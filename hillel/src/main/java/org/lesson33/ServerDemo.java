package org.lesson33;

public class ServerDemo {


    public static void main(String[] args) {

        Server server = new Server();

        Thread thread = new Thread(server);

        thread.start();

    }

}
