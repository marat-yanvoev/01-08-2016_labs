package sample.view.client;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.controller.AlertingSystemController;
import sample.controller.Database;
import sample.controller.Interface.AlertingSystem;
import sample.controller.Interface.TaskJournal;
import sample.controller.TaskJournalController;
import sample.model.OneTimeTask;
import sample.model.Task;
import sample.client.Main;

/**
 * Created by petka on 04.08.2016.
 */
public class RootLayoutController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemSave;
    @FXML
    private MenuItem menuItemAdd;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private MenuItem menuItemDelete;
    @FXML
    private MenuItem menuItemHelp;

    private Stage rootStage;
    private Main main;
    private Database database;
    private TaskJournal taskJournal;
    private AlertingSystem alertingSystem;

    public RootLayoutController() {}

    public void initialize() {
        database = Database.getInstance();
        taskJournal = TaskJournalController.getInstance();
        alertingSystem = AlertingSystemController.getInstance();
    }

    @FXML
    private void handleSave() {
        database.save(taskJournal.getTaskList());
    }

    @FXML
    private void handleClose(){
        rootStage.close();
        AlertingSystemController.getInstance().exitTray();
    }

    @FXML
    private void handleNew() {
        Task task = new OneTimeTask();
        boolean isClicked = main.showAddTaskDialog(task);
        if(isClicked) {
            taskJournal.add(task);
        }
    }

    @FXML
    private void handleEdit() {
        Task task = main.getTaskTable().getSelectionModel().getSelectedItem();
        if(task != null) {
            boolean isClicked = main.showAddTaskDialog(task);
            if(isClicked) {
                alertingSystem.editTimerTask(task, AlertingSystemController.TypeEdit.EDIT);
                main.getTaskTable().refresh();
            }
        }
    }

    @FXML
    private void handleDelete() {
        Task task = main.getTaskTable().getSelectionModel().getSelectedItem();
        if (task != null) {
            taskJournal.delete(task);
        }
    }



    public void setRootStage(Stage rootStage){
        this.rootStage = rootStage;
    }

    public Stage getRootStage() {
        return rootStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
