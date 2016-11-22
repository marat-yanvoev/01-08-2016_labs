package sample.controller;

import javafx.collections.ObservableList;
import sample.controller.Interface.DatabaseBehavior;
import sample.model.OneTimeTask;
import sample.model.SimpleTask;
import sample.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 01.09.2016.
 *
 * @author Evgeniy Tupikov
 */
public class SrlzDatabaseController implements DatabaseBehavior {

    private static SrlzDatabaseController instance;

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private List<SimpleTask> simpleTaskList;

    private final String fileName = "data.srlz.txt";

    public static synchronized SrlzDatabaseController getInstance() {
        if (instance == null) {
            instance = new SrlzDatabaseController();
        }
        return instance;
    }

    private SrlzDatabaseController() {}

    @Override
    public List<Task> load() {
        List<SimpleTask> simpleTaskList = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            simpleTaskList = (ArrayList)in.readObject();
            in.close();
            in = null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.simpleTaskList = simpleTaskList;
        return creatTaskList(simpleTaskList);
    }

    @Override
    public void save(ObservableList<Task> lines) {
        List<SimpleTask> simpleTaskList = new ArrayList<>();
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Task task : lines) {
                SimpleTask simpleTask = new SimpleTask(task.getTaskName(), task.getTaskStatus(), task.getTaskDescription(),
                                        task.getTaskContacts(), task.getTaskDateString(),
                                        Integer.toString(task.getTaskHour()), Integer.toString(task.getTaskMin()));
                simpleTaskList.add(simpleTask);
            }
            this.simpleTaskList = simpleTaskList;
            out.writeObject(simpleTaskList);
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> creatTaskList(List<SimpleTask> simpleTaskList) {
        List<Task> taskList = new ArrayList<>();
        for (SimpleTask task : simpleTaskList) {
            OneTimeTask oneTimeTask = new OneTimeTask(task.getTaskName(), task.getTaskStatus(), task.getTaskDescription(),
                    task.getTaskContacts(), task.getTaskDate(),
                    task.getTaskHour(), task.getTaskMin());
            taskList.add(oneTimeTask);
        }
        return taskList;
    }

    public synchronized List<SimpleTask> getSimpleTaskList() {
        return simpleTaskList;
    }

}
