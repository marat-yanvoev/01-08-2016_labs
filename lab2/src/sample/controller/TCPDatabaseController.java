package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import sample.client.Main;
import sample.client.controller.ClientController;
import sample.client.controller.TCPTask;
import sample.client.controller.interfaces.ClientBehavior;
import sample.controller.Interface.DatabaseBehavior;
import sample.controller.Interface.TaskJournal;
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
public class TCPDatabaseController implements DatabaseBehavior {

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
    private Main main;
    private Thread th;

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    private Object objectResponse;

    private TCPDatabaseController() {
        clientBehavior = ClientController.getInstance();
        simpleTaskList = new ArrayList<>();
        tcpTask = TCPTask.getInstance();
        tcpTask.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.RUNNING) {
                System.out.println("running task");
            }

            if (newValue == Worker.State.READY) {
                System.out.println("Task ready");
            }
            if (newValue == Worker.State.SUCCEEDED) {
                TaskJournalController.getInstance().setTaskList(tcpTask.getValue());
                main.setTaskList(tcpTask.getValue());
                main.startAlertingSystem();
                TCPTask.setNull();
            }

            if (newValue == Worker.State.CANCELLED) {
                System.out.println("canceled");
            }
        });
    }

    @Override
    public List<Task> load() {
        tcpTask.setQuery("1");
        th = new Thread(tcpTask);
        th.setDaemon(true);
        th.start();
        return null;
    }

    @Override
    public void save(ObservableList<Task> lines) {

    }

    public List<SimpleTask> getSimpleTaskList() {
        return simpleTaskList;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
