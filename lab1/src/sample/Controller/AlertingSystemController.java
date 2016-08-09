package sample.Controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import sample.Controller.Interface.AlertingSystem;
import sample.Main;
import sample.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by petka on 04.08.2016.
 */
public class AlertingSystemController implements AlertingSystem {

    private static volatile AlertingSystemController instance;

    private Timer timer;
    //private TimerTask timerTask;

    private SystemTray systemTray;
    private TrayIcon trayIcon;
    private PopupMenu popupMenu;
    private Calendar calendar;

    private Main main;
    private Stage rootStage;
    private ObservableList<Task> observableList;

    public static AlertingSystemController getInstance() {
        AlertingSystemController localInstance = instance;
        if (localInstance == null) {
            synchronized (AlertingSystemController.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AlertingSystemController();
                }
            }
        }
        return localInstance;
    }

    private AlertingSystemController() {
        this.calendar = Calendar.getInstance();

    }

    @Override
    public void runAlertingSystem() {

        runTimer();
        addListener();
    }

    @Override
    public void showSysTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("No suport system tray!");
            return;
        }

        popupMenu = new PopupMenu();
        trayIcon =
                new TrayIcon(createImage("Resources/images/bulb.gif", "tray icon"));
        systemTray = SystemTray.getSystemTray();

        MenuItem menuItem = new MenuItem("Help");
        java.awt.Menu disMenu = new java.awt.Menu("var");

        popupMenu.add(disMenu);
        disMenu.add(menuItem);

        trayIcon.setPopupMenu(popupMenu);



        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }


    }

    @Override
    public void removeTaskTimer(Task task) {
        System.out.println(task.cancel());
        System.out.println(timer.purge());
        timer.schedule(task, setTime(task).getTime());
    }

    private void addListener() {
        this.observableList.addListener((ListChangeListener<Task>) c -> {
            while (c.next()) {
                if (c.wasPermutated()) {
                    System.out.println("Permuted");
                    for (int i = c.getFrom(); i < c.getTo(); i++){

                    }
                } else if (c.wasUpdated()) {
                    System.out.println("Update");
                    for (int i = c.getFrom(); i < c.getTo(); i++){
                        Task task = c.getList().get(i);
                        task.cancel();
                        timer.schedule(task, setTime(task).getTime());
                    }
                } else if (c.wasReplaced()) {

                } else {
                    for (Task remitem : c.getRemoved()) {
                        System.out.println(remitem.toString() +
                                "\n delete" + setTime(remitem).getTime().toString());
                        remitem.cancel();
                    }
                    for (Task additem : c.getAddedSubList()) {
                        System.out.println(additem.toString() +
                                "\n added" + setTime(additem).getTime().toString());
                        timer.schedule(additem, setTime(additem).getTime());
                    }
                }
            }
        });
    }

    private Image createImage(String path, String description) {
        URL imageUrl = AlertingSystemController.class.getResource("../" + path);
        if (imageUrl == null) {
            System.err.println("resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageUrl, description)).getImage();
        }
    }

    private Calendar setTime (Task task) {
        LocalDate date = task.getTaskDate();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth(),
                task.getTaskHour(), task.getTaskMin(), 0);
        return calendar;
    }

    private void runTimer(){
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        for(Task task : observableList) {
            if (task.getTaskStatus().equals(Task.TaskStatus.READY.toString())) {
                timer.schedule(task, setTime(task).getTime());
            }
        }
    }

    private void closeTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void showMessage(String title, String text) {
        trayIcon.displayMessage(title, text, TrayIcon.MessageType.INFO);
    }

    public void exitTray() {
        systemTray.remove(trayIcon);
        System.exit(0);
    }

    public void setObservableList(ObservableList<Task> observableList) {
        this.observableList = observableList;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Stage getRootStage() {
        return rootStage;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }

//    public void setTimerTask(TimerTask timerTask) {
//        this.timerTask = timerTask;
//    }
}
