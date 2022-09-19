import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import model.constants.Status;
import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.TaskType;
import service.HistoryManager;
import service.TaskManager;
import service.implementation.FileBackedTasksManager;
import utils.Managers;

import java.util.*;


public class Main {
    public static final String FILE_PATH = "C:\\Users\\Win10_Game_OS\\IdeaProjects\\HomeworkTasks6\\test.csv";
    public static final File FILE = new File(FILE_PATH);

    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        FileBackedTasksManager fileBackedTasksManager = Managers.getDefaultFileBackedTasksManager(FILE);
//        fileBackedTasksManager.clearFile();
        Task task1 = new Task("Task 1 name", "Task 1 description", Status.NEW);
        Task task2 = new Task("Task 2 name", "Task 2 description", Status.NEW);
        Task task3 = new Task("Task 3 name", "Task 3 description", Status.NEW);
        Epic epic1 = new Epic("Epic 1 name", "Epic 1 description");
        Set.of(task1, task2, task3).forEach(fileBackedTasksManager::createTask);
        Task createdEpic = fileBackedTasksManager.createTask(epic1);
        Task subTask = new SubTask("subTask 1 name", "subTask 1 description", Status.NEW, createdEpic.getTaskId());
        fileBackedTasksManager.createTask(subTask);
//        FileBackedTasksManager.loadDataFromFile(FILE);



        //    Создаем 2 задачи
//        Task task = new Task("First Model.Task", "Model.Task description", Status.NEW);
//        Task task1 = new Task("Second Model.Task", "SecondTaskDescription", Status.DONE);
//        inMemoryTaskManager.createTask(task);
//        inMemoryTaskManager.createTask(task1);
//
//        //   Создаем 2 эпика
//        Epic epic = new Epic("First epic", "Model.Epic description");
//        Epic epic2 = new Epic("Second epic", "Model.Epic 2 description");
//        Epic createdEpic = inMemoryTaskManager.createEpic(epic);
//        Epic createdEpic2 = inMemoryTaskManager.createEpic(epic2);
//
//        Integer epicId = createdEpic.getTaskId();
//
//        SubTask subTask1 = new SubTask("New task1", "Description", Status.NEW, epicId);
//        SubTask subTask2 = new SubTask("New task2", "Description", Status.NEW, epicId);
//        SubTask subTask3 = new SubTask("New task3", "Description", Status.NEW, epicId);
//        inMemoryTaskManager.createSubTask(subTask1);
//        inMemoryTaskManager.createSubTask(subTask2);
//        inMemoryTaskManager.createSubTask(subTask3);
//
//        inMemoryTaskManager.getTasks();
//        inMemoryTaskManager.getEpics();
//        inMemoryTaskManager.getSubTasks();
//
//        inMemoryTaskManager.getTaskById(task.getTaskId());
//        inMemoryTaskManager.getEpicById(epic.getTaskId());
//        inMemoryTaskManager.getTaskById(task.getTaskId());
//        inMemoryTaskManager.getEpicById(epic.getTaskId());
      inMemoryTaskManager.getTaskById(task2.getTaskId());
//        inMemoryTaskManager.getEpicById(epic1.getTaskId());
        inMemoryTaskManager.getEpicById(epic1.getTaskId());
        inMemoryTaskManager.getTaskById(task1.getTaskId());
//        inMemoryHistoryManager.remove(task1.getTaskId());
//        inMemoryTaskManager.deleteTaskById(task.getTaskId());
//        inMemoryTaskManager.getSubTaskById(subTask1.getTaskId());
//        inMemoryTaskManager.deleteEpicById(createdEpic.getTaskId());
        System.out.println(inMemoryHistoryManager.getHistory());

    }
}

