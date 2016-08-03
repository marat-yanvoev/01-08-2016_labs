package sample.Controller;

import sample.Controller.Interface.FileDataBase;
import sample.Controller.Interface.TaskJournal;
import sample.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public class TaskJournalController implements TaskJournal{

    private static volatile TaskJournalController instance;

    private List<Task> taskList;
    private FileDataBase fileDB;

    public static TaskJournalController getInstance() {
        TaskJournalController localInstance = instance;
        if (localInstance == null) {
            synchronized (TaskJournalController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TaskJournalController();
                }
            }
        }
        return localInstance;
    }

    private TaskJournalController() {
        taskList = new ArrayList<>();
        fileDB = FileDataBaseController.getInstance();
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public void put(Task task) {

    }

    @Override
    public void complete(Task task) {

    }

    @Override
    public List<Task> getTaskList() {
        List<String> lines = fileDB.readFile();
        for(String line : lines){
            String[] value = line.split("/");

            taskList.add(new Task(value[0], value[1],
                    value[2], value[3], toLocalDate(value[4])));
        }

        return taskList;
    }


    private LocalDate toLocalDate(String value){
        String[] date = value.split(",");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);

        return LocalDate.of(year, month, day);
    }
}
