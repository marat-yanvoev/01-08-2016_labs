package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sample.Controller.FileDataBaseController;
import sample.Controller.Interface.FileDataBase;
import sample.Controller.Interface.TaskJournal;
import sample.Controller.TaskJournalController;
import sample.Main;
import sample.model.Task;

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
    private MenuItem menuItemAdd;
    @FXML
    private MenuItem menuItemEdit;
    @FXML
    private MenuItem menuItemDelete;
    @FXML
    private MenuItem menuItemHelp;

    private Stage rootStage;
    private Main main;
    private FileDataBase fileDB;
    private TaskJournal taskJournal;

    public RootLayoutController() {}

    public void initialize() {
        fileDB = FileDataBaseController.getInstance();
        taskJournal = TaskJournalController.getInstance();
    }

    @FXML
    private void handleClose(){
        rootStage.close();
    }

    @FXML
    private void handleNew() {
        Task task = new Task();
        boolean isClicked = main.showAddTaskDialog(task);
        if(isClicked) {
            main.getTaskData().add(task);
            fileDB.writeFile(taskJournal.createList(main.getTaskData()));
        }
    }

    @FXML
    private void handleEdit() {
        Task task = main.getTaskTable().getSelectionModel().getSelectedItem();
        if(task != null) {
            boolean isClicked = main.showAddTaskDialog(task);
            if(isClicked) {
                main.getTaskTable().refresh();
                fileDB.writeFile(taskJournal.createList(main.getTaskData()));
            }
        }
    }

    @FXML
    private void handleDelete() {
        Task task = main.getTaskTable().getSelectionModel().getSelectedItem();
        if (task != null) {
            main.getTaskData().remove(task);
            System.out.println(main.getTaskData().size());
            fileDB.writeFile(taskJournal.createList(main.getTaskData()));
        }
    }



    public void setRootStage(Stage rootStage){
        this.rootStage = rootStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
