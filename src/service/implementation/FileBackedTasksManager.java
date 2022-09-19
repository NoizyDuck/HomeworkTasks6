package service.implementation;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Epic;
import model.SubTask;
import model.Task;
import model.exceptions.ManagerReadException;
import model.exceptions.ManagerSaveException;
import service.TaskManager;

import java.io.File;
import utils.Managers;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private final File file; // = new File("save.csv");
    private Task task;
    public FileBackedTasksManager(File file) {
        this.file = file;
        if (file.length() != 0) {
            loadDataFromFile(file);
        }
    }

    public static FileBackedTasksManager loadDataFromFile(File file) {
        Path path = Path.of(file.getPath());
        List<String> readResult;
        try {
            readResult = Files.readAllLines(path);
        } catch (Exception ex) {
            throw new ManagerReadException(ex.getMessage());
        }
        TaskManager inMemoryManager = Managers.getDefault();
        inMemoryManager.importData(readResult);
        return null;
    }


    private void save() {
        String taskToWriteOnDisk = task.toString();
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            if (file.length() == 0) {
                fileWriter.write("id,type,name,status,description,epic\n");
            }
            fileWriter.write(taskToWriteOnDisk);
        } catch (Exception ex) {
            throw new ManagerSaveException(ex.getMessage());
        }
    }

    @Override
    public Task createTask(Task task) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        switch (task.getTaskType()) {
            case TASK:
                this.task = inMemoryTaskManager.createTask(task);
                break;
            case EPIC:
                this.task = inMemoryTaskManager.createEpic((Epic) task);
                break;
            case SUBTASK:
                this.task = inMemoryTaskManager.createSubTask((SubTask) task);
                break;
        }
        //TODO почему при вызове супер создается новый инстанс. Надо бы сделать так что бы новый инстанс не создавался.
//      super.createTask(task)
        save();
        return task;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        return super.createSubTask(subTask);
    }

    @Override
    public String getTasks() {
        return super.getTasks();
    }

    @Override
    public String getEpics() {
        return super.getEpics();
    }

    @Override
    public String getSubTasks() {
        return super.getSubTasks();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
    }

    @Override
    public void deleteAllSubTasks() {
        super.deleteAllSubTasks();
    }

    @Override
    public String getTaskById(int id) {
        return super.getTaskById(id);
    }

    @Override
    public String getEpicById(int id) {
        return super.getEpicById(id);
    }

    @Override
    public String getSubTaskById(int id) {
        return super.getSubTaskById(id);
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        super.updateEpic(id, epic);
    }

    @Override
    public void updateSubTask(int id, SubTask subTask) {
        super.updateSubTask(id, subTask);
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
    }

    @Override
    public void deleteSubTaskById(int id) {
        super.deleteSubTaskById(id);
    }

    @Override
    public String getSubTaskListByEpicId(int id) {
        return super.getSubTaskListByEpicId(id);
    }
}
