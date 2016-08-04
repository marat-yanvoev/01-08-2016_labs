package sample.model;

import javafx.beans.property.*;
import sun.util.resources.LocaleData;

import java.time.LocalDate;

/**
 * Created by petka on 01.08.2016.
 */
public class Task {

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

    /**
     * Конструктор по умолчанию
     */
    public Task(){ this(null, null, null, null, null); }

    public Task(String taskName, String taskStatus, String taskDescription, String taskContacts, LocalDate taskDate) {
        this.taskDate = new SimpleObjectProperty<LocalDate>(taskDate);
        this.taskContacts = new SimpleStringProperty(taskContacts);
        this.taskDescription = new SimpleStringProperty(taskDescription);
        this.taskStatus = new SimpleStringProperty(taskStatus);
        this.taskName = new SimpleStringProperty(taskName);
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

    @Override
    public String toString() {
        return getTaskName() + "/" +
                getTaskStatus() + "/" +
                getTaskDescription() + "/" +
                getTaskContacts() + "/" +
                dateToString(getTaskDate());
    }

    private String dateToString(LocalDate localDate) {
        return "" + localDate.getDayOfMonth() + "," +
                localDate.getMonthValue() + "," +
                localDate.getYear();
    }
}
