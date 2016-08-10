package sample.model;

import sample.Controller.AlertingSystemController;

import java.time.LocalDate;
import java.util.TimerTask;

/**
 * Created by petka on 09.08.2016.
 */
public class OneTimeTask extends Task{

    public OneTimeTask() {
        super(null, null, null, null, null, null, null);
    }

    public OneTimeTask(String taskName, String taskStatus, String taskDescription, String taskContacts,
                       String taskDate, String taskHour, String taskMin) {
        super(taskName, taskStatus, taskDescription, taskContacts, taskDate, taskHour, taskMin);
    }

}
