package sample.controller;

import javafx.collections.ObservableList;
import sample.controller.Interface.DatabaseBehavior;
import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 09.08.2016.
 */
public class Database {

    private static Database instance;

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static enum DatabaseType {
        FILE,
        //XML,
        SERIALIZE
    }

    private DatabaseType databaseType;
    private DatabaseBehavior databaseBehavior;

    private Database() {}

    public void save(ObservableList<Task> taskList) {
        databaseBehavior.save(taskList);
    }

    public List<Task> load() {
        return databaseBehavior.load();
    }

    public void set(DatabaseType databaseType) {
        this.databaseType = databaseType;
        createDatabaseBehavior();
    }

    private void createDatabaseBehavior() {
        if (databaseBehavior != null) {
            databaseBehavior = null;
        }
        switch (databaseType) {
            case FILE:
                databaseBehavior = FileDatabaseController.getInstance();
                break;

            //Для расширения вариантов сохранения
            /*case XML:

                break;*/
            case SERIALIZE:
                databaseBehavior = SrlzDatabaseController.getInstance();
                break;
            default:
                databaseBehavior = FileDatabaseController.getInstance();
        }
    }

}
