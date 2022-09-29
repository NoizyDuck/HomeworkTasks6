package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.HashMap;

public interface TaskManager {

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubTask(SubTask subTask);
    String getTask(HashMap<Integer,Task> map);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    String getTaskById(int id);

    String getEpicById(int id);

    String getSubTaskById(int id);

    void updateTask(int id, Task task);

    void updateEpic(int id, Epic epic);

    void updateSubTask(int id, SubTask subTask);

    void deleteTaskById(int Id);

    void deleteEpicById(int Id);

    void deleteSubTaskById(int Id);

    String getSubTaskListByEpicId(int id);
//  HashMap<Integer, Task> getTaskHashMap();
// HashMap<Integer, Epic> getEpicTaskHashMap();
//  HashMap<Integer, SubTask> getSubTaskHashMap();
// void importData(List<String> readResult);
}

