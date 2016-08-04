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

    private File f;

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

    /**
     * Создает файл, если не создан
     */
    private FileDataBaseController() {
        fileName = "data.tm";
        f = new File(fileName);
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читает файл по строкам
     *
     * @return - Возвращает Колекцию строк из файла
     *          каждая строка содержит информацию о задачи.
     */
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

    /**
     * Записывает строки в файл
     *
     * @param lines - Колекция строк. Создается с помощью метода createList.
     */
    @Override
    public void writeFile(List<String> lines) {
        try {
            f.delete();
            Path path = Files.write(Paths.get(fileName), lines, StandardCharsets.UTF_8,
                                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
