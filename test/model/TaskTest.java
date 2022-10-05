package model;

import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    LocalDateTime startDate = LocalDateTime.of(2022, 9, 12, 23, 10);
    Task task =
            new Task(1, "name", "description", Status.NEW, TaskType.TASK, startDate, 60L);

    @Test
    public void getEndTimeIfDayChanges() {
        LocalDateTime expectedEndTime = startDate.plusMinutes(60L);
        LocalDateTime actualEndTime = task.getEndTime();
        assertEquals(expectedEndTime, actualEndTime);
    }

    @Test
    public void nullCheckIllegalStateThrows() {
        assertThrows(IllegalStateException.class, task::getEndTime, "Duration is null, have to set duration first");
    }

    @Test
    public void shouldReturnStatusNew(){
        Status expectedTaskStatus = Status.NEW;
        Status actualTaskStatus = task.getStatus();
        assertEquals(expectedTaskStatus, actualTaskStatus);
    }

    @Test
    public void shouldReturnTaskId(){
        Integer expectedTaskId = 1;
        Integer actualTaskId = task.getTaskId();
        assertEquals(expectedTaskId,actualTaskId);
    }
    @Test
    public void shouldReturnTaskName(){
        String expectedTaskName = "name";
        String actualTaskName = task.getTaskName();
        assertEquals(expectedTaskName,actualTaskName);
    }
    @Test
    public void shouldReturnTaskDescription(){
        String expectedTaskDescription = "description";
        String actualTaskDescription = task.getTaskDescription();
        assertEquals(expectedTaskDescription,actualTaskDescription);
    }

}