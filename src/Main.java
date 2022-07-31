import model.constants.Status;
import model.Epic;
import model.SubTask;
import model.Task;
import service.HistoryManager;
import service.TaskManager;
import utils.Managers;

import java.util.*;


public class Main {


    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

        //    Создаем 2 задачи
        Task task = new Task("First Model.Task", "Model.Task description", Status.NEW);
        Task task1 = new Task("Second Model.Task", "SecondTaskDescription", Status.DONE);
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.createTask(task1);

        //   Создаем 2 эпика
        Epic epic = new Epic("First epic", "Model.Epic description");
        Epic epic2 = new Epic("Second epic", "Model.Epic 2 description");
        Epic createdEpic = inMemoryTaskManager.createEpic(epic);
        Epic createdEpic2 = inMemoryTaskManager.createEpic(epic2);

        Integer epicId = createdEpic.getTaskId();

        SubTask subTask1 = new SubTask("New task1", "Description", Status.NEW, epicId);
        SubTask subTask2 = new SubTask("New task2", "Description", Status.NEW, epicId);
        SubTask subTask3 = new SubTask("New task3", "Description", Status.NEW, epicId);
        inMemoryTaskManager.createSubTask(subTask1);
        inMemoryTaskManager.createSubTask(subTask2);
        inMemoryTaskManager.createSubTask(subTask3);

        inMemoryTaskManager.getTasks();
        inMemoryTaskManager.getEpics();
        inMemoryTaskManager.getSubTasks();

        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getEpicById(epic.getTaskId());
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getEpicById(epic.getTaskId());
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getEpicById(epic.getTaskId());
        inMemoryTaskManager.getEpicById(epic2.getTaskId());
        inMemoryTaskManager.getTaskById(task1.getTaskId());
        inMemoryHistoryManager.remove(task1.getTaskId());
        inMemoryTaskManager.deleteTaskById(task.getTaskId());
        inMemoryTaskManager.getSubTaskById(subTask1.getTaskId());
        inMemoryTaskManager.deleteEpicById(createdEpic.getTaskId());
        System.out.println(inMemoryHistoryManager.getHistory());

    }
}

