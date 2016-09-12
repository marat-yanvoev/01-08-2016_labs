package sample.controller.Interface;

import sample.controller.AlertingSystemController;
import sample.model.Task;

/**
 * Created by petka on 08.08.2016.
 */
public interface AlertingSystem {
    public void runAlertingSystem();
    public void showMessage(String title, String text);
    public void showSysTray();
    public int showDialog(Task task);
    public void editTimerTask(Task task, AlertingSystemController.TypeEdit typeEdit);
}
