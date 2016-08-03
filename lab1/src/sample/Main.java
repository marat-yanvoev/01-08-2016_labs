package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Controller.Interface.TaskJournal;
import sample.Controller.TaskJournalController;
import sample.model.Task;
import sample.view.TaskOverviewController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TaskJournal taskJournal;

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

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTaskOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TaskOverview.fxml"));
            AnchorPane ap = (AnchorPane) loader.load();

            rootLayout.setCenter(ap);

            TaskOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Task> getTaskData(){
        return taskData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
