package sample.server.controller;

import sample.server.controller.interfaces.Authorizationable;

import java.io.*;

/**
 * Created by petka on 09.11.2016.
 *
 * @author Evgeniy Tupikov
 */
public class AuthController implements Authorizationable {

    private static volatile AuthController instance;

    public static synchronized AuthController getInstance() {
        if (instance == null) {
            instance = new AuthController();
        }
        return instance;
    }

    private volatile String verificMessage;
    private final String fileName = "user.txt";
    private File f;
    private FileReader fr;
    private BufferedReader reader;

    private AuthController() {
        f = new File(fileName);
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String getVerificMessage() {
        return verificMessage;
    }

    @Override
    public synchronized boolean verification(String name, String pass) {
        try {
            String localPass = getUserDate(name);
            System.out.println(localPass);
            if (localPass != null) {
                if (localPass.equals(pass)) {
                    verificMessage = "Successful";
                    return true;
                } else {
                    verificMessage = "Incorrect password";
                    return false;
                }
            } else {
                verificMessage = "User not found";
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUserDate(String name) throws IOException {
        try {
            String line;
            fr = new FileReader(f);
            reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] userDate = line.split("/");
                if (userDate[0].equals(name)) {
                    return userDate[1];
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            reader.close();
            fr.close();
        }
    }

}
