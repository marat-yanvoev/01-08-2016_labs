package sample.client;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.client.controller.ClientController;
import sample.client.controller.ReloadTaskController;
import sample.client.controller.TCPTask;
import sample.controller.AlertingSystemController;
import sample.controller.Database;
import sample.controller.Interface.TaskJournal;
import sample.controller.TCPDatabaseController;
import sample.controller.TaskJournalController;
import sample.model.SimpleTask;
import sample.model.Task;
import sample.view.client.AddAndEditTaskDialogController;
import sample.view.client.RootLayoutController;
import sample.view.client.TaskOverviewController;

import java.io.IOException;

/**
 * Created by petka on 01.09.2016.
 *
 * @author Evgeniy Tupikov
 *
 * Пока тестовый класс, для проверки передачи объекта серверу
 *
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TaskJournal taskJournal;
    private Database database;

    private TableView<Task> taskTable;

    private ObservableList<Task> taskList;

    public Main() {
        taskJournal = TaskJournalController.getInstance();
        TCPDatabaseController.getInstance().setMain(this);
        database = Database.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task manager Client");

        initRootLayout();
        showTaskOverview();

        ClientController.getInstance().connect();

        database.set(Database.DatabaseType.TCP);
        taskJournal.start();
        ReloadTaskController reloadTaskController = new ReloadTaskController(this);
        reloadTaskController.start();
    }

    /**
     * Создает первоначальное окно приложения с меню и контейнером.
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/client/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setRootStage(primaryStage);
            controller.setMain(this);

            AlertingSystemController.getInstance().setRootStage(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавляет представление журнала в главное окно.
     */
    private void showTaskOverview() {
        try {
            //Загружаем вьюху
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/client/TaskOverview.fxml"));
            AnchorPane ap = (AnchorPane) loader.load();

            rootLayout.setCenter(ap);

            //получаем контроллев вьюхи
            TaskOverviewController controller = loader.getController();
            controller.setMain(this);

            //Получаем объект таблицы
            this.taskTable = controller.getTaskTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Окно добавления и редактирования задачи.
     * Для добавления новой задачи передается новый объект с пустым конструктором.
     *
     * @param task задача для редактирования.
     * @return
     */
    public boolean showAddTaskDialog(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/client/AddTaskDialog.fxml"));
            AnchorPane ap = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            String title = task == null ? "New task" : "Edit task";
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(ap);
            dialogStage.setScene(scene);

            AddAndEditTaskDialogController controller = loader.getController();
            controller.setAddTaskStage(dialogStage);
            controller.setTask(task);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void startAlertingSystem() {
        System.out.println("start alerting...");
        AlertingSystemController asc = AlertingSystemController.getInstance();
        asc.setObservableList(taskList);
        asc.showSysTray();
        asc.runAlertingSystem();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Task> getTaskList(){
        return taskList;
    }

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }
}
