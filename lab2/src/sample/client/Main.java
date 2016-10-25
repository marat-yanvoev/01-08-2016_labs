package sample.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by petka on 01.09.2016.
 *
 * @author Evgeniy Tupikov
 *
 * Пока тестовый класс, для проверки передачи объекта серверу
 *
 */
public class Main {

    private static Socket clientSocket;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static String clientMessage;



    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("Connect...");
        clientSocket = new Socket(addr, 4444);
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());


        try {
            System.out.println("send message...");
            objectOutputStream.writeObject("Hello!!!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
