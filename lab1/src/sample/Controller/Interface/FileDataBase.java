package sample.Controller.Interface;

import sample.model.Task;

import java.util.List;

/**
 * Created by petka on 02.08.2016.
 */
public interface FileDataBase {
    public List<String> readFile();
    public void writeFile(List<String> lines);
}
