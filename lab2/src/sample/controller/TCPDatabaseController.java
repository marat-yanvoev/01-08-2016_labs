package sample.controller;

import javafx.collections.ObservableList;
import sample.client.controller.ClientController;
import sample.client.controller.TCPTask;
import sample.client.controller.interfaces.ClientBehavior;
import sample.controller.Interface.DatabaseBehavior;
import sample.model.OneTimeTask;
import sample.model.SimpleTask;
import sample.model.Task;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by petka on 01.11.2016.
 *
 * @author Evgeniy Tupikov
 */
class TCPDatabaseController implements DatabaseBehavior {

    private static TCPDatabaseController instance;

    public static TCPDatabaseController getInstance() {
        if (instance == null) {
            instance = new TCPDatabaseController();
        }
        return instance;
    }

    private ClientBehavior clientBehavior;
    private List<SimpleTask> simpleTaskList;
    private TCPTask tcpTask;

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    private Object objectResponse;

    private TCPDatabaseController() {
        clientBehavior = ClientController.getInstance();
        simpleTaskList = new ArrayList<>();
        tcpTask = TCPTask.getInstance();
    }

    @Override
    public List<Task> load() {
        tcpTask.setQuery("1");
        new Thread(tcpTask).start();
        return null;
    }

    @Override
    public void save(ObservableList<Task> lines) {

    }

    public List<SimpleTask> getSimpleTaskList() {
        return simpleTaskList;
    }
}
