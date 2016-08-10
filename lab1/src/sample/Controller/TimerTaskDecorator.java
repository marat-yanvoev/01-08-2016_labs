package sample.Controller;

import sample.Controller.Interface.AlertingSystem;
import sample.Controller.Interface.TaskJournal;
import sample.model.Task;

import javax.swing.*;
import java.util.TimerTask;

/**
 * Created by petka on 10.08.2016.
 */
public class TimerTaskDecorator extends TimerTask {

    private Task task;
    private AlertingSystem alertingSystem;
    private TaskJournal taskJournal;

    public TimerTaskDecorator(Task task) {
        this.task = task;
        alertingSystem = AlertingSystemController.getInstance();
        taskJournal = TaskJournalController.getInstance();
    }

    @Override
    public void run() {
        alertingSystem.showMessage(task.getTaskName(), task.getTaskDescription());
        int result = alertingSystem.showDialog(task);
        if (result == JOptionPane.YES_OPTION) {
            taskJournal.put(task);
        } else {
            taskJournal.complete(task);
        }
    }

    public Task getTask() {
        return task;
    }
}
