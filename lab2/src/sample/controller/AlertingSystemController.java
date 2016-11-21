package sample.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import sample.client.controller.TCPTask;
import sample.controller.Interface.AlertingSystem;
import sample.server.Main;
import sample.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by petka on 04.08.2016.
 */
public class AlertingSystemController implements AlertingSystem {

    public static enum TypeEdit {
        EDIT,
        PUT,
        CANCEL
    }

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
    private List<TimerTaskDecorator> timerTaskDecorators;
    private TCPTask tcpTask;

    public static synchronized AlertingSystemController getInstance() {
        if (instance == null) {
            instance = new AlertingSystemController();
        }
        return instance;
    }

    private AlertingSystemController() {
        this.calendar = Calendar.getInstance();
        timerTaskDecorators = new ArrayList<>();

    }

    @Override
    public void runAlertingSystem() {
        tcpTask = TCPTask.getInstance();
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
    public int showDialog(Task task) {
        return JOptionPane.showConfirmDialog(null, task.getTaskDescription() +
                "\n Отложить на 10 секунд?", task.getTaskName(), JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void editTimerTask(Task task, TypeEdit typeEdit) {
        TimerTask timerTask = timerTaskDecorators.get(getOfTask(task));
        timerTask.cancel();
        timerTaskDecorators.remove(timerTask);
        timerTaskDecorators.add(new TimerTaskDecorator(task));
        switch (typeEdit) {
            case EDIT:
                System.out.println("EDIT");
                timer.schedule(timerTaskDecorators.get(getOfTask(task)), setTime(task).getTime());
                break;
            case PUT:
                System.out.println("PUT");
                timer.schedule(timerTaskDecorators.get(getOfTask(task)), 10000);
                break;
            case CANCEL:
                System.out.println("CANCEL");
                break;
        }

    }

    /*
     * Refractoring
     */
    private void addListener() {
        this.observableList.addListener((ListChangeListener<Task>) c -> {
            while (c.next()) {
                if (c.wasPermutated()) {
                    System.out.println("Permuted");
                    for (int i = c.getFrom(); i < c.getTo(); i++){}
                } else if (c.wasUpdated()) {
                    System.out.println("Update");
                } else if (c.wasReplaced()) {
                } else {
                    c.getRemoved().forEach(this::removedItem);
                    c.getAddedSubList().forEach(this::addedSubList);
                    timer.purge();
                }
            }
        });
    }

    private void removedItem(Task remitem) {
        TimerTask timerTask = timerTaskDecorators.get(getOfTask(remitem));
        System.out.println(remitem.toString() +
                "\n delete: " + setTime(remitem).getTime().toString());
        timerTask.cancel();
        timerTaskDecorators.remove(timerTask);
        queryToServer("del/" + remitem.getTaskName());
    }

    private void addedSubList(Task additem) {
        timerTaskDecorators.add(new TimerTaskDecorator(additem));
        System.out.println(additem.toString() +
                "\n added: " + setTime(additem).getTime().toString());
        timer.schedule(timerTaskDecorators.get(getOfTask(additem)), setTime(additem).getTime());
        queryToServer(additem.toString());
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
            timerTaskDecorators.add(new TimerTaskDecorator(task));
            TimerTask timerTask = timerTaskDecorators.get(getOfTask(task));
            if (task.getTaskStatus().equals(Task.TaskStatus.READY.toString())) {
                timer.schedule(timerTask, setTime(task).getTime());
            }
        }
    }

    private void closeTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
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

    private int getOfTask(Task task) {
        for (TimerTaskDecorator timerTask : timerTaskDecorators) {
            if (timerTask.getTask().equals(task)) {
                return timerTaskDecorators.indexOf(timerTask);
            }
        }

        return 0;
    }

    private void queryToServer(String query) {
        System.out.println(query);
        TCPTask.setNull();
        tcpTask = TCPTask.getInstance();
        tcpTask.setQuery(query);
        System.out.println(tcpTask.isCancelled());
        Thread th = new Thread(tcpTask);
        th.setDaemon(true);
        th.start();
    }

//    public void setTimerTask(TimerTask timerTask) {
//        this.timerTask = timerTask;
//    }
}
