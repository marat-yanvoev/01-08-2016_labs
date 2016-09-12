package sample.server.controller;

import org.omg.CORBA.Object;
import sample.server.controller.interfaces.ClientConnection;

import java.net.Socket;

/**
 * Created by petka on 07.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class ConnectController extends Thread implements ClientConnection {

    private Socket clientSocket;

    public ConnectController(Socket socket) {
        clientSocket = socket;
        start();
    }

    public void run() {

    }

    @Override
    public void send(Object object) {

    }

    @Override
    public Object get() {
        return null;
    }

}