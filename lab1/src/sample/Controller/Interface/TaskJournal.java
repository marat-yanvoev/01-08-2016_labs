package sample.Controller.Interface;

import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public interface TaskJournal {
    public void add(Task task);
    public void delete(Task task);
    public void put(Task task);
    public void complete(Task task);
    public List<Task> getTaskList();
}
