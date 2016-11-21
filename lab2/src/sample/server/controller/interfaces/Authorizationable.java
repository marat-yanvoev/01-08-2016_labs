package sample.server.controller.interfaces;

/**
 * Created by petka on 09.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public interface Authorizationable {
    boolean verification(String name, String pass);
}