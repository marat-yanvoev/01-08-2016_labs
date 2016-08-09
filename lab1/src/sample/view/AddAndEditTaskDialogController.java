package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import sample.model.Task;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 03.08.2016.
 */
public class AddAndEditTaskDialogController {

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
    private Task task;

    private boolean okClicked = false;

    public AddAndEditTaskDialogController() {}

    public void initialize() {}

    public void setAddTaskStage(Stage addTaskStage) {
        this.addTaskStage = addTaskStage;
    }

    public void setTask (Task task) {
        this.task = task;

        taskName.setText(task.getTaskName());
        taskDate.setValue(task.getTaskDate());
        taskDescription.setText(task.getTaskDescription());
        taskContacts.setText(task.getTaskContacts());
        hour.setText("" + task.getTaskHour());
        minute.setText("" + task.getTaskMin());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleAdd(){
        if (isValid()) {
            task.setTaskName(taskName.getText());
            task.setTaskDate(taskDate.getValue());
            task.setTaskDescription(taskDescription.getText());
            task.setTaskContacts(taskContacts.getText());
            task.setTaskStatus(Task.TaskStatus.READY.toString());
            task.setTaskHour(hour.getText());
            task.setTaskMin(minute.getText());

            okClicked = true;
            addTaskStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        addTaskStage.close();
    }

    private boolean isValid() {

        errorMessage = "";

        if(taskDate.getValue() == null){
            errorMessage += "Pick date!\n";
        }

        List<TextField> tfList = new ArrayList<>();
        tfList.add(taskName);
        tfList.add(taskContacts);
        for (TextField t : tfList) {
            if(t.getText() == null || t.getLength() <= 0){
                errorMessage += "no expected " + t.getClass().getName() + "\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(addTaskStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}
