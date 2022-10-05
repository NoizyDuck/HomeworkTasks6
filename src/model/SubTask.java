package model;

import model.constants.Status;
import model.constants.TaskType;

import java.time.LocalDateTime;


public class SubTask extends Task {
    private int epicId;

    public SubTask(String taskName, String taskDescription, Status status, TaskType taskType,
                   LocalDateTime startTime, Long duration, int epicId) {
        super(taskName, taskDescription, status, taskType, startTime, duration);
        this.epicId = epicId;
    }

    public SubTask(String taskName, String taskDescription, Status status, int epicId) {
        super(taskName, taskDescription, status, TaskType.SUBTASK);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + epicId;
    }


}
