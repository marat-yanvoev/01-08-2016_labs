package sample.client.controller;

import sample.client.controller.interfaces.ClientBehavior;
import sample.model.SimpleTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 01.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class ClientController extends Thread implements ClientBehavior{

    private static ClientController instance;

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private InetAddress addr;
    private int port;
    private String clientMessage;
    private List<SimpleTask> simpleTaskList = new ArrayList<>();
    private Object objectResponse;

    private ClientController() {
        try {
            addr = InetAddress.getByName("localhost");
            port = 4444;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(String clientMessage) {
        try {
            System.out.println("Connect to " + addr.toString() + ":" + port);
            clientSocket = new Socket(addr, port);

            //порядок важен
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            this.clientMessage = clientMessage;
            System.out.println("Start thread");
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getResponse() {
        System.out.println("Get Response");
        return objectResponse;
    }

    @Override
    public void disconnect() {
        try {
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        processingSteps(clientMessage);
    }

    private void processingSteps(String clientMessage) {
        if (clientMessage == null) {
            //Исключение
            throw new UnsupportedOperationException();
        } else {
            try {
                System.out.println("Send message : " + clientMessage);
                objectOutputStream.writeObject(clientMessage);
                System.out.println("Response...");
                objectResponse = objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
