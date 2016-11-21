package sample.server.controller;

import org.omg.CORBA.Object;
import sample.controller.Database;
import sample.controller.Interface.TaskJournal;
import sample.controller.SrlzDatabaseController;
import sample.controller.TaskJournalController;
import sample.model.OneTimeTask;
import sample.model.Task;
import sample.server.controller.interfaces.ClientConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created on 07.09.2016.
 * @version 0.1
 *
 * Контроллер соединения с клиентом.
 * Обрабатывает запросы от клиента.
 *
 * @see sample.server.controller.interfaces.ClientConnection
 *
 * @author Evgeniy Tupikov
 */
public class ConnectController extends Thread implements ClientConnection {

    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String clientMessage;
    private SrlzDatabaseController serializeDB;
    private boolean authorization = false;
    private String taskName;
    private TaskJournal taskJournal;
    private Database database;
    private String[] tempMess;
    private String tempTaskString;

    public ConnectController(Socket socket) throws IOException {
        clientSocket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(clientSocket.toString());
        serializeDB = SrlzDatabaseController.getInstance();
        taskJournal = TaskJournalController.getInstance();
        start();
    }

    public void run() {
        try {
            while (authorization != true) {
                clientMessage = (String) objectInputStream.readObject();
                System.out.println(clientMessage);
                String[] userDate = clientMessage.split("/");
                System.out.println("verification... \n" +
                        "id: " + userDate[0] +
                        "\npass: " + userDate[1]);
                authorization = AuthController.getInstance().verification(userDate[0], userDate[1]);
                if (authorization == false) {
                    objectOutputStream.writeObject(AuthController.getInstance().getVerificMessage() +
                    "Please, try again...");
                } else {
                    objectOutputStream.writeObject(AuthController.getInstance().getVerificMessage());
                    break;
                }
            }
            while (authorization == true) {
                System.out.println("wait message from client...");
                clientMessage = (String) objectInputStream.readObject();
                System.out.println(clientMessage);


                tempMess = clientMessage.split("/");
                if (tempMess.length == 2) {
                    taskName = tempMess[1];
                    clientMessage = tempMess[0];
                } else if (tempMess.length > 2) {
                    tempTaskString = clientMessage;
                    clientMessage = "add";
                }


                processingSteps(clientMessage);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Object object) {

    }

    @Override
    public Object get() {
        return null;
    }

    /**
     *
     * @param message - Message from client.
     *                '1' - get Task list,
     *                '0' - close connect.
     */
    private void processingSteps(String message) {
        switch (message) {
            case "1":
                sendTaskList();
                break;
            case "0":
                connectionClose();
                break;
            case "del":
                System.out.println("delete " + taskName);
                //delete task
                deleteTask();
                serializeDB.save(taskJournal.getTaskList());
                sendTaskList();
                break;
            case "add":
                System.out.println("add " + tempTaskString);
                //add task
                addTask(tempTaskString);
                serializeDB.save(taskJournal.getTaskList());
                sendTaskList();
                break;
            default:
                //ignored
                break;
        }
    }

    private void addTask(String taskString) {
        String[] s = taskString.split("/");
        System.out.println(s.toString());
        Task task = new OneTimeTask(
                s[0], s[1], s[2], s[3], s[4], s[5], s[6]
        );
        taskJournal.add(task);
    }

    private void deleteTask() {
        taskJournal.delete(findTask(taskName));
    }

    /**
     * Отправка коллекции POJO объектов задач.
     */
    private void sendTaskList() {
        try {
            System.out.println("Send response...");
            objectOutputStream.writeObject(serializeDB.getSimpleTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Завершение соединения с клиентов.
     * Высвобождение ресурсов.
     */
    private void connectionClose() {
        try {
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();
            System.out.println("Connection close...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Task findTask(String taskName) {
        for (Task task : taskJournal.getTaskList()) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

}