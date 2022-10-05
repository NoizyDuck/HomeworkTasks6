package model;

import model.constants.Status;
import org.junit.jupiter.api.Test;
import service.implementation.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.*;
class SubTaskTest {
    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Epic epic = new Epic("epic", "epicDescription");
    SubTask subTask = new SubTask("subTask", "subTask description", Status.DONE, 1);

    @Test
    public void shouldReturnLinkedEpicId(){
        inMemoryTaskManager.createEpic(epic);
        inMemoryTaskManager.createSubTask(subTask);
        int expectedEpicId = 1;
        int actualEpicId = subTask.getEpicId();
        assertEquals(expectedEpicId,actualEpicId);
    }
}