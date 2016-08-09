package sample.Controller;

import javafx.collections.ObservableList;
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

    /**
     * Создает колекцию объектов задач из Колекции строк
     * полченных из файла.
     * @return - Колекцию объектов {@link Task}
     */
    @Override
    public List<Task> getTaskList() {
        List<String> lines = fileDB.readFile();
        for(String line : lines){
            String[] value = line.split("/");

            taskList.add(new Task(value[0], value[1],
                    value[2], value[3], toLocalDate(value[4]), value[5], value[6]));
        }

        return taskList;
    }

    /**
     * Преобразует Колекцию объектов в Колекцию строк этих объектов.
     * toString у Task переопределен.
     *
     * @param taskList
     * @return
     */
    @Override
    public List<String> createList(ObservableList<Task> taskList) {
        List<String> stringList = new ArrayList<>();
        for (Task task : taskList) {
            stringList.add(task.toString());
        }

        return stringList;
    }

    /**
     * Преобразует строку со временем в объект LocalDate
     * @param value
     * @return
     */
    private LocalDate toLocalDate(String value){
        String[] date = value.split(",");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);

        return LocalDate.of(year, month, day);
    }

}
