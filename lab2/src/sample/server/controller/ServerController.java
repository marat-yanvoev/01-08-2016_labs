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

    private final int port = 4444;
    private static ServerSocket serverSocket;

    private ServerController() {}

    public ServerController getInstance() {
        if(instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    @Override
    public void start(){
        try {
            ConnectController cc = new ConnectController(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }
}
