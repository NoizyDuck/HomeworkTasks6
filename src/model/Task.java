package model;

import model.constants.Status;
import model.constants.TaskType;

public class Task {
    private Integer taskId;
    private String taskName;
    private String taskDescription;
    private Status status;
    private TaskType taskType;

    public Task(String taskName, String taskDescription, TaskType taskType) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Task(String taskName, String taskDescription, Status status) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskType = TaskType.TASK;
    }

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskType = TaskType.TASK;
    }

    public Task(String taskName, String taskDescription, Status status, TaskType taskType) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskType = taskType;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s \n", taskId, taskType, taskName, status, taskDescription);
    }

}
