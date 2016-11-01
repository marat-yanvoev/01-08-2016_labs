package sample.server.controller;

import sample.server.controller.interfaces.TMServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by petka on 01.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class ServerController extends Thread implements TMServer {

    private static ServerController instance;

    private final int port = 4444;
    private static ServerSocket serverSocket;

    private ServerController() {
        try {
            System.out.println("run server. Port = " + port);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServerController getInstance() {
        if(instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void run() {
        try {
            while (true) {
                new ConnectController(serverSocket.accept());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startServer(){
        start();
    }

    @Override
    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
