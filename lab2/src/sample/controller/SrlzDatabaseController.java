package sample.controller;

import javafx.collections.ObservableList;
import sample.controller.Interface.DatabaseBehavior;
import sample.model.OneTimeTask;
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
        List<Task> list = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            while (list.add((OneTimeTask)in.readObject())) {}
            in.close();
            in = null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(ObservableList<Task> lines) {
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            for (Task task : lines) {
                out.writeObject(task);
            }
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
