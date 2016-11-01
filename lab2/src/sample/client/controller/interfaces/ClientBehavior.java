package sample.client.controller.interfaces;

import sample.model.SimpleTask;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by petka on 01.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface ClientBehavior {
    void connect(String clientMessage);
    Object getResponse();
    void disconnect();
}
