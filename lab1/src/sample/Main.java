package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.Interface.TaskJournal;
import sample.Controller.TaskJournalController;
import sample.model.Task;
import sample.view.AddAndEditTaskDialogController;
import sample.view.RootLayoutController;
import sample.view.TaskOverviewController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TaskJournal taskJournal;

    private TableView<Task> taskTable;

    private ObservableList<Task> taskData = FXCollections.observableArrayList();

    public Main() {
        taskJournal = TaskJournalController.getInstance();
        taskData.addAll(taskJournal.getTaskList());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task manager");

        initRootLayout();
        showTaskOverview();
    }

    /**
     * Создает первоначальное окно приложения с меню и контейнером.
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setRootStage(primaryStage);
            controller.setMain(this);

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
            loader.setLocation(Main.class.getResource("view/TaskOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("view/AddTaskDialog.fxml"));
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Task> getTaskData(){
        return taskData;
    }

    public TableView<Task> getTaskTable() {
        return taskTable;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
