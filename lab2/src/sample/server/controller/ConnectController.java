package sample.server.controller;

import org.omg.CORBA.Object;
import sample.server.controller.interfaces.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by petka on 07.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class ConnectController extends Thread implements ClientConnection {

    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String clientMessage;

    public ConnectController(Socket socket) throws IOException {
        clientSocket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        start();
    }

    public void run() {
        try {
            clientMessage = (String)objectInputStream.readObject();
            System.out.println(clientMessage);
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

}