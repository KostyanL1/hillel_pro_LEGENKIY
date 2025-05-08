package org.lesson33;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {



        try (Socket socket = new Socket("localhost", 1111)) {
            try(PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)){
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()){
                    printWriter.println(scanner.nextLine());
                }
            }

        }
    }
}