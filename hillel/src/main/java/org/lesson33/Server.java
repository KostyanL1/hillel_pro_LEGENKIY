package org.lesson33;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Server implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    private List<ActiveConnection> activeConnections = new ArrayList<>();

    private int index;


    @Override
    public void run() {
        try (ServerSocket serverSocket = new  ServerSocket(1111)) {
            LOGGER.info("Сервер запущено на порту 1111.");
            while (true){
                Socket accept = serverSocket.accept();
                handleConnection(accept);
            }
        } catch (IOException e) {
            LOGGER.info("При спробі підключення виникла помилка.");
            throw new RuntimeException(e);
        }
    }



    public void handleConnection(Socket clientSocket){

            new Thread(() -> {
                if (clientSocket.isConnected()){
                    addConnection(clientSocket);
                }
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
                    String message;
                    while ((message = bufferedReader.readLine()) !=  null ){
                        if (message.equals("/exit")){
                            ActiveConnection activeConnection = activeConnections.stream().filter(i -> i.getSocket().equals(clientSocket.getInetAddress() + ":" + clientSocket.getPort())).findFirst().get();
                            clientSocket.close();
                            deleteConnection(activeConnection);
                        }
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();
    }


     public synchronized void addConnection(Socket clientSocket){
         index++;
         ActiveConnection activeConnection = new ActiveConnection(index, LocalDateTime.now(), clientSocket.getInetAddress() + ":" + clientSocket.getPort());
         activeConnections.add(activeConnection);
         LOGGER.info("Клієнт підключився. {}", activeConnection.toString());
     }

     public synchronized void deleteConnection(ActiveConnection activeConnection) throws IOException {
        activeConnections.remove(activeConnection);
         LOGGER.info("Клієнт від'єднався. {}", activeConnection.getSocket());
     }
}
