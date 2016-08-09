package sample.Controller.Interface;

import javafx.collections.ObservableList;
import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 08.08.2016.
 */
public interface AlertingSystem {
    public void runAlertingSystem();
    public void showSysTray();
    public void removeTaskTimer(Task task);
}
