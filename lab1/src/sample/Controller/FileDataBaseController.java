package sample.Controller;

import javafx.collections.ObservableList;
import sample.Controller.Interface.DatabaseBehavior;
import sample.model.OneTimeTask;
import sample.model.Task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Контроллер работы с текстовыми файлами
 */
public class FileDatabaseController implements DatabaseBehavior {


    private static volatile FileDatabaseController instance;

    private final String fileName;

    private File f;

    public static FileDatabaseController getInstance() {
        FileDatabaseController localInstance = instance;
        if (localInstance == null) {
            synchronized (FileDatabaseController.class){
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FileDatabaseController();
                }
            }
        }
        return localInstance;
    }

    /**
     * Создает файл, если не создан
     */
    private FileDatabaseController() {
        fileName = "data.tm";
        f = new File(fileName);
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Task> load() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createTaskList(lines);
    }


    @Override
    public void save(ObservableList<Task> lines) {
        try {
            f.delete();
            Path path = Files.write(Paths.get(fileName), createStringList(lines), StandardCharsets.UTF_8,
                                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> createTaskList(List<String> stringList) {
        List<Task> taskList = new ArrayList<>();
        for (String line: stringList) {
            String[] s = line.split("/");
            Task task = new OneTimeTask(
                    s[0], s[1], s[2], s[3], s[4], s[5], s[6]
            );
            taskList.add(task);
        }
        return taskList;
    }

    private List<String> createStringList (ObservableList<Task> taskList) {
        List<String> stringList = new ArrayList<>();
        for (Task task : taskList) {
            stringList.add(task.toString());
        }
        return stringList;
    }

}
