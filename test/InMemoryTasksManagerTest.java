import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import org.junit.jupiter.api.Test;
import service.TaskManager;
import service.implementation.InMemoryTaskManager;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryTasksManagerTest extends TaskManagerTest<InMemoryTaskManager>{

    Task task =
            new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                    LocalDateTime.of(2022, 9, 12, 23, 25), 60L);
    Epic epic = new Epic("epic", "epicDescription");
    SubTask subTask = new SubTask("subtask","subTask description",Status.NEW,TaskType.SUBTASK,
            LocalDateTime.of(2022,9,12,9,15),15L,1);


    @Override
    protected InMemoryTaskManager createManager() {
        return new InMemoryTaskManager();
    }


    @Test
    @Override
    void createTask() {
      Task expectedTask = manager.createTask(task);
      assertEquals(task,expectedTask);
    }

    @Test
    @Override
    void createEpic() {
    Epic expectedEpic = manager.createEpic(epic);
    assertEquals(epic,expectedEpic);
    }

    @Test
    @Override
    void createSubTask(){
        manager.createEpic(epic);
        SubTask expectedSubTask = manager.createSubTask(subTask);
        assertEquals(subTask,expectedSubTask);
    }
    @Test
    @Override
    void getTask() {
    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    taskHashMap.put(task.getTaskId(),task);
    String expectedTask = manager.getTask(taskHashMap);
    assertEquals(expectedTask,task.toString());
    }
    @Test
    @Override
    void deleteAllTasks() {
        manager.createTask(task);
        manager.deleteAllTasks();
        boolean expectedValue = manager.getTaskHashMap().isEmpty();
        assertTrue(expectedValue);

    }
    @Test
    @Override
    void deleteAllEpics() {
        manager.createEpic(epic);
        manager.deleteAllEpics();
        boolean expectedValue = manager.getEpicTaskHashMap().isEmpty();
        assertTrue(expectedValue);
    }
    @Test
    @Override
    void deleteAllSubTasks() {
        manager.createEpic(epic);
        manager.createSubTask(subTask);
        manager.deleteAllSubTasks();
        boolean expectedValue = manager.getSubTaskHashMap().isEmpty();
        assertTrue(expectedValue);
    }
    @Test
    @Override
    void getTaskById() {
        manager.createTask(task);
        String expectedTask = manager.getTaskById(task.getTaskId());
        assertEquals(expectedTask, task.toString());

    }
    @Test
    @Override
    void getEpicById() {
        manager.createEpic(epic);
        String expectedEpic = manager.getEpicById(epic.getTaskId());
        assertEquals(expectedEpic, epic.toString());

    }
    @Test
    @Override
    void getSubTaskById() {
        manager.createEpic(epic);
        manager.createSubTask(subTask);
        String expectedSubTask = manager.getSubTaskById(subTask.getTaskId());
        assertEquals(expectedSubTask, subTask.toString());
    }

    @Override
    void updateTask() {


    }

    @Override
    void updateEpic() {

    }

    @Override
    void updateSubTask() {

    }
    @Test
    @Override
    void deleteTaskById() {
    manager.createTask(task);
    manager.deleteTaskById(task.getTaskId());
    boolean expectedValue = manager.getTaskHashMap().isEmpty();
    assertTrue(expectedValue);
    }

    @Test
    @Override
    void deleteEpicById() {
        manager.createEpic(epic);
        manager.deleteEpicById(epic.getTaskId());
        boolean expectedValue = manager.getEpicTaskHashMap().isEmpty();
        assertTrue(expectedValue);

    }
    @Test
    @Override
    void deleteSubTaskById() {
        manager.createEpic(epic);
        manager.createSubTask(subTask);
        manager.deleteSubTaskById(subTask.getTaskId());
        boolean expectedValue = manager.getSubTaskHashMap().isEmpty();
        assertTrue(expectedValue);
    }

}
