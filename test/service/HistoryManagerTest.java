package service;

import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import org.junit.jupiter.api.Test;
import service.implementation.InMemoryTaskManager;
import utils.Managers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    HistoryManager historyManager = Managers.getDefaultHistory();
    LocalDateTime startDate = LocalDateTime.of(2022, 9, 12, 23, 10);
    Task task =
            new Task(1, "name", "description", Status.NEW, TaskType.TASK, startDate, 60L);

    @Test
    public void shouldReturnEmptyHistoryList(){
        boolean expectedList = true;
        boolean actualList = historyManager.getHistory().isEmpty();
        assertEquals(expectedList, actualList);
    }
    @Test
    public void shouldReturnAddedTask (){
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.getTaskById(task.getTaskId());
        boolean expectedValue = true;
        boolean actualValue = historyManager.getHistory().contains(task);
        assertEquals(expectedValue,actualValue);
    }

    @Test

    public void shouldReplaceSameTaskFromHistory(){
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getTaskById(task.getTaskId());
        int expectedHistoryListSize = 1;
        int actualHistoryListSize = historyManager.getHistory().size();
        assertEquals(expectedHistoryListSize,actualHistoryListSize);
    }
@Test
    public void shouldDeleteTaskFromHead(){
        Task task1 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022,9,12,23,25), 60L);
        Task task2 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022,9,12,23,50), 60L);
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.createTask(task1);
        inMemoryTaskManager.createTask(task2);
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getTaskById(task1.getTaskId());
        inMemoryTaskManager.getTaskById(task2.getTaskId());
        historyManager.remove(task.getTaskId());
        List<Task> expectedHistoryTaskList = List.of(task1,task2);
        List<Task> actualHistoryTaskList = historyManager.getHistory();
        assertEquals(expectedHistoryTaskList,actualHistoryTaskList);
    }

    @Test
    public void shouldDeleteTaskFromMiddle() {
        Task task1 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022, 9, 12, 23, 25), 60L);
        Task task2 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022, 9, 12, 23, 50), 60L);
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.createTask(task1);
        inMemoryTaskManager.createTask(task2);
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getTaskById(task1.getTaskId());
        inMemoryTaskManager.getTaskById(task2.getTaskId());
        historyManager.remove(task1.getTaskId());
        List<Task> expectedHistoryTaskList = List.of(task, task2);
        List<Task> actualHistoryTaskList = historyManager.getHistory();
        assertEquals(expectedHistoryTaskList, actualHistoryTaskList);
    }

    @Test
    public void shouldDeleteTaskFromTail() {
        Task task1 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022, 9, 12, 23, 25), 60L);
        Task task2 =
                new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                        LocalDateTime.of(2022, 9, 12, 23, 50), 60L);
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.createTask(task1);
        inMemoryTaskManager.createTask(task2);
        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getTaskById(task1.getTaskId());
        inMemoryTaskManager.getTaskById(task2.getTaskId());
        historyManager.remove(task2.getTaskId());
        List<Task> expectedHistoryTaskList = List.of(task, task1);
        List<Task> actualHistoryTaskList = historyManager.getHistory();
        assertEquals(expectedHistoryTaskList, actualHistoryTaskList);
    }


    }