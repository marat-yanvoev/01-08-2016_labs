package sample.Controller.Interface;

import javafx.collections.ObservableList;
import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public interface DatabaseBehavior {
    public List<Task> load();
    public void save(ObservableList<Task> lines);
}