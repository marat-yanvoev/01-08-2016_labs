package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.controller.Interface.AlertingSystem;
import sample.controller.Interface.TaskJournal;
import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public class TaskJournalController implements TaskJournal{

    private static volatile TaskJournalController instance;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    private Database database;
    private AlertingSystem alertingSystem;

    private TableView tableView;

    public static synchronized TaskJournalController getInstance() {
        if (instance == null) {
            instance = new TaskJournalController();
        }
        return instance;
    }

    private TaskJournalController() {
        database = Database.getInstance();
        alertingSystem = AlertingSystemController.getInstance();

    }

    @Override
    public void add(Task task) {
        taskList.add(task);
    }

    @Override
    public void delete(Task task) {
        taskList.remove(task);
    }

    @Override
    public void put(Task task) {
        taskList.get(taskList.indexOf(task)).setTaskStatus("Put");
        alertingSystem.editTimerTask(task, AlertingSystemController.TypeEdit.PUT);
    }

    @Override
    public void complete(Task task) {
        taskList.get(taskList.indexOf(task)).setTaskStatus("End");
        alertingSystem.editTimerTask(task, AlertingSystemController.TypeEdit.CANCEL);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public void start() {
        List<Task> tempTaskList = database.load();
        if (tempTaskList != null) {
            System.out.println("load main thread...");
            taskList.addAll(tempTaskList);
        } else {
            System.out.println("Background thread");
        }
    }

}
