import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import service.implementation.FileBackedTasksManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager>{
    File file = new File("src/resources/test.csv");
    Task task =
            new Task(1, "name", "description", Status.NEW, TaskType.TASK,
                    LocalDateTime.of(2022, 9, 12, 23, 25), 60L);
    Epic epic = new Epic("epic", "epicDescription");
    SubTask subTask = new SubTask("subTask", "subTask description", Status.DONE, 1);

    @AfterEach
    void deleteFile() {
        file.delete();
    }
    @Override
    protected FileBackedTasksManager createManager() throws IOException {
        return new FileBackedTasksManager(new File("src/resources/test.csv"));
    }
    @Test
    public void saveTasksTest() throws IOException {
    manager.createTask(task);
    String csv = Files.readString(Path.of(file.getPath()));
    String [] lines = csv.split("\n");
    String expectedValue = lines[1];
    assertEquals(expectedValue, task.toString());
    }

    @Test
    public void loadDataFromFileTest() {
        manager.createTask(task);
        String taskById = manager.getTaskById(task.getTaskId());
        assertEquals(task.toString(), taskById);
    }

    @Override
    void createTask() {
    }

    @Override
    void createEpic() {
    }

    @Override
    void createSubTask() {
    }

    @Override
    void getTask() {
    }

    @Override
    void deleteAllTasks() {
    }

    @Override
    void deleteAllEpics() {
    }

    @Override
    void deleteAllSubTasks() {
    }

    @Override
    void getTaskById() {
    }

    @Override
    void getEpicById() {
    }

    @Override
    void getSubTaskById() {
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

    @Override
    void deleteTaskById() {
    }

    @Override
    void deleteEpicById() {
    }

    @Override
    void deleteSubTaskById() {
    }
}
