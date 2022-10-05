import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import service.HistoryManager;
import service.TaskManager;
import service.implementation.FileBackedTasksManager;
import service.implementation.InMemoryTaskManager;
import utils.Managers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;


public class Main {


    public static void main(String[] args) throws IOException {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        File file = new File("src/resources/test.csv");
        FileBackedTasksManager fileBackedTasksManager = Managers.getDefaultFileBackedTasksManager(file);
        Task task1 = new Task("taskName1","taskDescription1", Status.NEW,TaskType.TASK,LocalDateTime.of(2022, 9, 12, 21, 10),60L);
        Task task2 = new Task("taskName2","taskDescription2", Status.NEW,TaskType.TASK,LocalDateTime.of(2022, 9, 12, 22, 10),60L);
        Task task3 = new Task("taskName3","taskDescription3", Status.NEW,TaskType.TASK,LocalDateTime.of(2022, 9, 12, 23, 10),60L);
        Epic epic1 = new Epic("Epic 1 name", "Epic 1 description");
        Set.of(task1, task2, task3).forEach(fileBackedTasksManager::createTask);

        Epic createdEpic = fileBackedTasksManager.createEpic(epic1);

        SubTask subTask = new SubTask("subtask","subTask description",Status.NEW, TaskType.SUBTASK,
                LocalDateTime.of(2022,9,12,9,15),15L, createdEpic.getTaskId());
        fileBackedTasksManager.createSubTask(subTask);
        fileBackedTasksManager.getEpicById(epic1.getTaskId());
        fileBackedTasksManager.getTaskById(task1.getTaskId());
        fileBackedTasksManager.getSubTaskById(subTask.getTaskId());
        System.out.println(inMemoryHistoryManager.getHistory());
        fileBackedTasksManager.getPrioritizedTasks();


    }
}

