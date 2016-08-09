package sample.model;

import javafx.beans.property.*;
import sample.Controller.AlertingSystemController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.TimerTask;

/**
 * Created by petka on 01.08.2016.
 */
public class Task extends TimerTask {

    public static enum TaskStatus {
        READY("Ready"),
        END("End"),
        PUT("Put");

        private final String text;

        TaskStatus(String text) {
            this.text = text;
        }

        @Override
        public String toString(){
            return text;
        }
    }

    private final StringProperty taskName;
    private final StringProperty taskStatus;
    private final StringProperty taskDescription;
    private final StringProperty taskContacts;
    private final ObjectProperty<LocalDate> taskDate;
    private final StringProperty taskHour;
    private final StringProperty taskMin;

    /**
     * Конструктор по умолчанию
     */
    public Task(){ this(null, null, null, null, null, null, null); }

    @Override
    public void run() {

        AlertingSystemController.getInstance().showMessage(
                getTaskName(), getTaskDescription());

    }

    public Task(String taskName, String taskStatus, String taskDescription, String taskContacts,
                LocalDate taskDate, String taskHour, String taskMin) {
        this.taskDate = new SimpleObjectProperty<LocalDate>(taskDate);
        this.taskContacts = new SimpleStringProperty(taskContacts);
        this.taskDescription = new SimpleStringProperty(taskDescription);
        this.taskStatus = new SimpleStringProperty(taskStatus);
        this.taskName = new SimpleStringProperty(taskName);
        this.taskHour = new SimpleStringProperty(taskHour);
        this.taskMin = new SimpleStringProperty(taskMin);
    }

    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public String getTaskStatus() {
        return taskStatus.get();
    }

    public StringProperty taskStatusProperty() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus.set(taskStatus);
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }

    public StringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription.set(taskDescription);
    }

    public String getTaskContacts() {
        return taskContacts.get();
    }

    public StringProperty taskContactsProperty() {
        return taskContacts;
    }

    public void setTaskContacts(String taskContacts) {
        this.taskContacts.set(taskContacts);
    }

    public LocalDate getTaskDate() {
        return taskDate.get();
    }

    public ObjectProperty<LocalDate> taskDateProperty() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate.set(taskDate);
    }

    public int getTaskHour() {
        return taskHour.get() == null ? 0 : Integer.parseInt(taskHour.get());
    }

    public StringProperty taskHourProperty() {
        return taskHour;
    }

    public void setTaskHour(String taskHour) {
        this.taskHour.set(taskHour);
    }

    public int getTaskMin() {
        return taskMin.get() == null ? 0 : Integer.parseInt(taskMin.get());
    }

    public StringProperty taskMinProperty() {
        return taskMin;
    }

    public void setTaskMin(String taskMin) {
        this.taskMin.set(taskMin);
    }

    @Override
    public String toString() {
        return getTaskName() + "/" +
                getTaskStatus() + "/" +
                getTaskDescription() + "/" +
                getTaskContacts() + "/" +
                dateToString(getTaskDate()) + "/" +
                getTaskHour() + "/" +
                getTaskMin();
    }

    private String dateToString(LocalDate localDate) {
        return "" + localDate.getDayOfMonth() + "," +
                localDate.getMonthValue() + "," +
                localDate.getYear();
    }
}
