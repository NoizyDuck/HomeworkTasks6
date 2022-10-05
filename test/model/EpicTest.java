package model;

import model.constants.Status;
import model.constants.TaskType;
import org.junit.jupiter.api.Test;
import service.implementation.InMemoryTaskManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Epic epic = new Epic("epic", "epicDescription");


    @Test
    public void shouldReturnEpicName(){
        String expectedEpicName = "epic";
        String actualEpicName = epic.getTaskName();
        assertEquals(expectedEpicName, actualEpicName);
    }

    @Test
    public void shouldReturnEpicDescription(){
        String expectedEpicDescription = "epicDescription";
        String actualEpicDescription = epic.getTaskDescription();
        assertEquals(expectedEpicDescription,actualEpicDescription);
    }

    @Test
    public void shouldReturnEpicStatusNew(){
        SubTask subTask = new SubTask("subTask", "subTask description", Status.NEW, 1);
        inMemoryTaskManager.createEpic(epic);
        inMemoryTaskManager.createSubTask(subTask);
        Status expectedEpicStatus = Status.NEW;
        Status actualEpicStatus = epic.getStatus() ;
        assertEquals(expectedEpicStatus,actualEpicStatus);
    }
    @Test
    public void shouldReturnEpicStatusDone(){
        SubTask subTask = new SubTask("subTask", "subTask description", Status.DONE, 1);
        inMemoryTaskManager.createEpic(epic);
        inMemoryTaskManager.createSubTask(subTask);
        Status expectedEpicStatus = Status.DONE;
        Status actualEpicStatus = epic.getStatus() ;
        assertEquals(expectedEpicStatus,actualEpicStatus);

    }

    @Test
    public void shouldReturnEpicStatusInProgress(){
        SubTask subTask1 = new SubTask("subTask", "subTask description", Status.NEW, 1);
        SubTask subTask2 = new SubTask("subTask", "subTask description", Status.DONE, 1);
        inMemoryTaskManager.createEpic(epic);
        inMemoryTaskManager.createSubTask(subTask1);
        inMemoryTaskManager.createSubTask(subTask2);
        Status expectedEpicStatus = Status.IN_PROGRESS;
        Status actualEpicStatus = epic.getStatus() ;
        assertEquals(expectedEpicStatus,actualEpicStatus);
    }
    @Test
    public void shouldReturnEpicStatusDoneIfNoSubTasks (){
        inMemoryTaskManager.createEpic(epic);
        Status expectedEpicStatus = Status.NONE;
        Status actualEpicStatus = epic.getStatus() ;
        assertEquals(expectedEpicStatus,actualEpicStatus);
    }
}