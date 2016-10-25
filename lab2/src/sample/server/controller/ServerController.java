package sample.server.controller;

import sample.server.controller.interfaces.TMServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by petka on 01.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class ServerController implements TMServer {

    private static ServerController instance;

    private final int port = 5454;
    private static ServerSocket serverSocket;

    public ServerController() {
        try {
            System.out.println("run server. Port = " + port);
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerController getInstance() {
        if(instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    @Override
    public void start(){
        try {
            new ConnectController(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
