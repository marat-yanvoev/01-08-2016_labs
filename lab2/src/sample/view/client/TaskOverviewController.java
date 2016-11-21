package sample.view.client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.client.controller.TCPTask;
import sample.controller.Interface.TaskJournal;
import sample.controller.TaskJournalController;
import sample.model.Task;
import sample.client.Main;

import java.time.LocalDate;

/**
 * Created by petka on 02.08.2016.
 */
public class TaskOverviewController {

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> taskName;
    @FXML
    private TableColumn<Task, LocalDate> taskDate;

    @FXML
    private Label taskStatus;
    @FXML
    private TextArea taskDescription;
    @FXML
    private Label taskContacts;

    private Main main;
    private TaskJournal taskJournal;

    public TaskOverviewController() {

    }

    @FXML
    private void initialize(){
        taskJournal = TaskJournalController.getInstance();
        taskTable.itemsProperty().bind(TCPTask.getInstance().valueProperty());
        //taskTable.setItems(taskJournal.getTaskList());
        taskName.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskDate.setCellValueFactory(cellData -> cellData.getValue().taskDateProperty());

        showTaskDetail(null);

        taskTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showTaskDetail(newValue))
        );

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    private void showTaskDetail(Task task){
        if(task != null) {
            taskStatus.setText(task.getTaskStatus());
            taskDescription.setText(task.getTaskDescription());
            taskContacts.setText(task.getTaskContacts());
        } else {
            taskStatus.setText("");
            taskDescription.setText("");
            taskContacts.setText("");
        }
    }



}
