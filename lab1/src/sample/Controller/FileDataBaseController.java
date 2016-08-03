package sample.Controller;

import sample.Controller.Interface.FileDataBase;
import sample.model.Task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public class FileDataBaseController implements FileDataBase {

    private static volatile FileDataBaseController instance;

    private final String fileName;

    public static FileDataBaseController getInstance() {
        FileDataBaseController localInstance = instance;
        if (localInstance == null) {
            synchronized (FileDataBaseController.class){
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FileDataBaseController();
                }
            }
        }
        return localInstance;
    }

    private FileDataBaseController() {
        fileName = "data.tm";
        File f = new File(fileName);
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void writeFile(List<String> lines) {
        try {
            Path path = Files.write(Paths.get(fileName), lines, StandardCharsets.UTF_8,
                                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
