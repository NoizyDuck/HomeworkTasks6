import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import service.HistoryManager;
import service.TaskManager;
import service.implementation.FileBackedTasksManager;
import utils.Managers;

import java.io.File;
import java.io.IOException;
import java.util.Set;


public class Main {


    public static void main(String[] args) throws IOException {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        File file = new File("src/resources/test.csv");
        FileBackedTasksManager fileBackedTasksManager = Managers.getDefaultFileBackedTasksManager(file);
        Task task1 = new Task("Task 1 name", "Task 1 description", Status.NEW);
        Task task2 = new Task("Task 2 name", "Task 2 description", Status.NEW);
        Task task3 = new Task("Task 3 name", "Task 3 description", Status.NEW);
        Epic epic1 = new Epic("Epic 1 name", "Epic 1 description");
        Set.of(task1, task2, task3).forEach(fileBackedTasksManager::createTask);

        Epic createdEpic = fileBackedTasksManager.createEpic(epic1);

        SubTask subTask = new SubTask("subTask 1 name", "subTask 1 description", Status.NEW, createdEpic.getTaskId());
        fileBackedTasksManager.createSubTask(subTask);
        fileBackedTasksManager.getEpicById(epic1.getTaskId());
        fileBackedTasksManager.getTaskById(task1.getTaskId());
        fileBackedTasksManager.getSubTaskById(subTask.getTaskId());
        System.out.println(inMemoryHistoryManager.getHistory());



    }
}

