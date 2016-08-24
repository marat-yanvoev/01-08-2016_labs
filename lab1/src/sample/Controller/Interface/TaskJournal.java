package sample.controller.Interface;

import javafx.collections.ObservableList;
import sample.model.Task;

/**
 * Created by petka on 02.08.2016.
 */
public interface TaskJournal {
    public void start();
    public void add(Task task);
    public void delete(Task task);
    public void put(Task task);
    public void complete(Task task);
    public ObservableList<Task> getTaskList();
}
