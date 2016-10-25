package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by petka on 24.10.2016.
 *
 * @author Evgeniy Tupikov
 *
 * Тестовый клас для проверки сокетов
 *
 */
public class TestServerPort {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static int port = 4444;

    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    public static void main(String[] args) {
        try {
            System.out.println("Run in port = " + port);
            serverSocket = new ServerSocket(port);
            System.out.println("wait...");
            socket = serverSocket.accept();
            System.out.println(socket.toString());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("read messages...");
            System.out.println("Message >> " + (String)objectInputStream.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
