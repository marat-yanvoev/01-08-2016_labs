package sample.server.controller.interfaces;

import org.omg.CORBA.Object;

/**
 * Created by petka on 07.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface ClientConnection {
    public void send(Object object);
    public Object get();
}
