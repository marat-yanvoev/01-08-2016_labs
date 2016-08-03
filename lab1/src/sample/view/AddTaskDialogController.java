package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Task;

/**
 * Created by petka on 03.08.2016.
 */
public class AddTaskDialogController {

    @FXML
    private TextField taskName;
    @FXML
    private DatePicker taskDate;
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;
    @FXML
    private TextArea taskDescription;
    @FXML
    private TextField taskContacts;

    private String errorMessage;
    private Stage addTaskStage;
    private Task newTask;

    public AddTaskDialogController() {}

    public void initialize() {}

    public void setAddTaskStage(Stage addTaskStage) {
        this.addTaskStage = addTaskStage;
    }

    //private Task

}
