package model;

import model.constants.Status;
import model.constants.TaskType;

import java.time.LocalDateTime;

public class Task {
    private Integer taskId;
    private String taskName;
    private String taskDescription;
    private Status status = Status.NONE;
    private TaskType taskType;
    private LocalDateTime startTime;
    private Long duration;

    public Task(String taskName, String taskDescription, TaskType taskType) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
    }

    public Task(Integer taskId, String taskName, String taskDescription, Status status, TaskType taskType, LocalDateTime startTime, Long duration) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskType = taskType;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Task(String taskName, String taskDescription, Status status, TaskType taskType, LocalDateTime startTime, Long duration) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.taskType = taskType;
        this.startTime = startTime;
        this.duration = duration;
    }


    public LocalDateTime getEndTime () {
        if (duration != null) {
            return startTime.plusMinutes(duration);

        }
        throw new IllegalStateException("Duration is null, have to set duration first");
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

    public String getTaskDescription() {
        return taskDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", taskId, taskType, taskName, status, taskDescription, startTime,duration);
    }

}
