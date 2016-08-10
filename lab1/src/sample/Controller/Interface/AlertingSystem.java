package sample.Controller.Interface;

import javafx.collections.ObservableList;
import sample.Controller.AlertingSystemController;
import sample.model.Task;

import java.util.List;

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
