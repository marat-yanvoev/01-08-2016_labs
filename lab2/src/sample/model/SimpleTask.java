package sample.model;

import java.io.Serializable;

/**
 *
 * Простой POJO класс. Служит как контейнер для сеализации
 * и передачи его по сети. С помощью него мы создаем объект Task.
 *
 * Created by petka on 15.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class SimpleTask implements Serializable{

    private String taskName;
    private String taskStatus;
    private String taskDescription;
    private String taskContacts;
    private String taskDate;
    private String taskHour;
    private String taskMin;

    public SimpleTask(String taskName, String taskStatus, String taskDescription, String taskContacts, String taskDate, String taskHour, String taskMin) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskDescription = taskDescription;
        this.taskContacts = taskContacts;
        this.taskDate = taskDate;
        this.taskHour = taskHour;
        this.taskMin = taskMin;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskContacts() {
        return taskContacts;
    }

    public void setTaskContacts(String taskContacts) {
        this.taskContacts = taskContacts;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(String taskHour) {
        this.taskHour = taskHour;
    }

    public String getTaskMin() {
        return taskMin;
    }

    public void setTaskMin(String taskMin) {
        this.taskMin = taskMin;
    }
}
