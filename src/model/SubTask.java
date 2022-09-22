package model;

import model.constants.Status;
import model.constants.TaskType;


public class SubTask extends Task {
    private int epicId;

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
        return String.format("%s,%s,%s,%s,%s,%s \n", super.getTaskId(), super.getTaskType(), super.getTaskName(), super.getStatus(), super.getTaskDescription(), epicId);
    }
}
