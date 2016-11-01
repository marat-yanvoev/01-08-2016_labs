package sample.server.controller;

import org.omg.CORBA.Object;
import sample.controller.SrlzDatabaseController;
import sample.controller.TaskJournalController;
import sample.server.controller.interfaces.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created on 07.09.2016.
 * @version 0.1
 *
 * Контроллер соединения с клиентом.
 * Обрабатывает запросы от клиента.
 *
 * @see sample.server.controller.interfaces.ClientConnection
 *
 * @author Evgeniy Tupikov
 */
public class ConnectController extends Thread implements ClientConnection {

    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String clientMessage;
    private SrlzDatabaseController serializeDB;

    public ConnectController(Socket socket) throws IOException {
        clientSocket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(clientSocket.toString());
        serializeDB = SrlzDatabaseController.getInstance();
        start();
    }

    public void run() {
        try {
            clientMessage = (String)objectInputStream.readObject();
            System.out.println(clientMessage);
            processingSteps(clientMessage);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Object object) {

    }

    @Override
    public Object get() {
        return null;
    }

    /**
     *
     * @param message - Message from client.
     *                '1' - get Task list,
     *                '0' - close connect.
     */
    private void processingSteps(String message) {
        switch (message) {
            case "1":
                sendTaskList();
                break;
            case "0":
                connectionClose();
                break;
            case "del":
                //delete task
                break;
            case "add":
                //add task
                break;
            default:
                //ignored
                break;
        }
    }

    /**
     * Отправка коллекции POJO объектов задач.
     */
    private void sendTaskList() {
        try {
            System.out.println("Send response...");
            objectOutputStream.writeObject(serializeDB.getSimpleTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Завершение соединения с клиентов.
     * Высвобождение ресурсов.
     */
    private void connectionClose() {
        try {
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();
            System.out.println("Connection close...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}