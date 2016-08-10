package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Controller.Interface.AlertingSystem;
import sample.Controller.Interface.TaskJournal;
import sample.model.Task;

/**
 * Created by petka on 02.08.2016.
 */
public class TaskJournalController implements TaskJournal{

    private static volatile TaskJournalController instance;

    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    private Database database;
    private AlertingSystem alertingSystem;

    private TableView tableView;

    public static TaskJournalController getInstance() {
        TaskJournalController localInstance = instance;
        if (localInstance == null) {
            synchronized (TaskJournalController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TaskJournalController();
                }
            }
        }
        return localInstance;
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
        taskList.addAll(database.load());
    }

}
