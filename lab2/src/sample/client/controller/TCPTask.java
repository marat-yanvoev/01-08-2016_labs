package sample.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.client.controller.interfaces.ClientBehavior;
import sample.controller.Database;
import sample.model.OneTimeTask;
import sample.model.SimpleTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 07.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class TCPTask extends Task<ObservableList<sample.model.Task>> {

    private static TCPTask instance;

    public static TCPTask getInstance() {
        if (instance == null) {
            instance = new TCPTask();
        }
        return instance;
    }

    public static void setNull() {
        instance = null;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private String query;
    private ClientBehavior client;

    private TCPTask() {
        client = ClientController.getInstance();
    }

    @Override
    protected ObservableList<sample.model.Task> call() throws Exception {
        System.out.println("loading Task list...");
        updateMessage("loading Task list...");
        ObservableList<sample.model.Task> taskObservableList = FXCollections.observableArrayList();
        client.sendQuery(query);
        taskObservableList.addAll(createTaskList((ArrayList)client.getResponse()));
        System.out.println(taskObservableList.get(0).getTaskName());
        return taskObservableList;
    }

    private List<sample.model.Task> createTaskList(List<SimpleTask> simpleTaskList) {
        List<sample.model.Task> taskList = new ArrayList<>();
        for (SimpleTask task : simpleTaskList) {
            OneTimeTask oneTimeTask = new OneTimeTask(task.getTaskName(), task.getTaskStatus(), task.getTaskDescription(),
                    task.getTaskContacts(), task.getTaskDate(),
                    task.getTaskHour(), task.getTaskMin());
            taskList.add(oneTimeTask);
        }
        return taskList;
    }
}
