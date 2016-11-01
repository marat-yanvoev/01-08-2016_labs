package sample.controller;

import javafx.collections.ObservableList;
import sample.client.controller.ClientController;
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

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    private Object objectResponse;

    private TCPDatabaseController() {
        clientBehavior = ClientController.getInstance();
        simpleTaskList = new ArrayList<>();
    }

    @Override
    public List<Task> load() {
        clientBehavior.connect("1");
        try {
            ClientController.getInstance().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleTaskList = (ArrayList)clientBehavior.getResponse();
        System.out.println(simpleTaskList.get(0).toString());
        clientBehavior.disconnect();
        return createTaskList(simpleTaskList);
    }

    @Override
    public void save(ObservableList<Task> lines) {

    }

    private List<Task> createTaskList(List<SimpleTask> simpleTaskList) {
        List<Task> taskList = new ArrayList<>();
        for (SimpleTask task : simpleTaskList) {
            OneTimeTask oneTimeTask = new OneTimeTask(task.getTaskName(), task.getTaskStatus(), task.getTaskDescription(),
                    task.getTaskContacts(), task.getTaskDate(),
                    task.getTaskHour(), task.getTaskMin());
            taskList.add(oneTimeTask);
        }
        return taskList;
    }

    public List<SimpleTask> getSimpleTaskList() {
        return simpleTaskList;
    }
}
