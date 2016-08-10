package sample.Controller;

import javafx.collections.ObservableList;
import sample.Controller.Interface.DatabaseBehavior;
import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 09.08.2016.
 */
public class Database {

    private static volatile Database instance;

    public static Database getInstance() {
        Database localInstance = instance;
        if (localInstance == null){
            synchronized (Database.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Database();
                }
            }
        }
        return localInstance;
    }

    public static enum DatabaseType {
        FILE,
        //XML,
        //SERIALIZE
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

                break;
            case SERIALIZE:

                break;*/
            default:
                databaseBehavior = FileDatabaseController.getInstance();
        }
    }

}
