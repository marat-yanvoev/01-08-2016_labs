package sample.client.controller;

import sample.client.Main;
import sample.client.controller.interfaces.ClientBehavior;
import sample.model.Task;

import javafx.scene.control.TableView;

/**
 * Created by Nastya on 21.11.2016.
 */
public class ReloadTaskController extends Thread{

    private TCPTask tcpTask;
    private TableView<Task> tableView;

    public ReloadTaskController(Main main) {
        this.tableView = main.getTaskTable();
    }

    public void run () {
        while (true) {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TCPTask.setNull();
            tcpTask = TCPTask.getInstance();
            tableView.itemsProperty().bind(tcpTask.valueProperty());
            tcpTask.setQuery("1");
            tcpTask.run();
        }
    }
}
