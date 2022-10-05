package model;

import model.constants.Status;
import model.constants.TaskType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasks = new ArrayList<>();
    private LocalDateTime endTime;

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Epic(String taskName, String taskDescription, TaskType taskType, List<Integer> subTasks) {
        super(taskName, taskDescription, taskType);
        this.subTasks = subTasks;
    }
    public Epic(String taskName, String taskDescription, Status status, List<Integer> subTasks) {
        super(taskName, taskDescription, status);
        this.subTasks = subTasks;
    }

    public Epic(String taskName, String taskDescription, List<Integer> subTasks) {
        super(taskName, taskDescription);
        this.subTasks = subTasks;
    }

    public Epic(String taskName, String taskDescription, Status status, TaskType taskType, List<Integer> subTasks) {
        super(taskName, taskDescription, status, taskType);
        this.subTasks = subTasks;
    }

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription, TaskType.EPIC);
    }


    public List<Integer> getSubTasksIds() {
        return subTasks;
    }

    public void linkSubtask(SubTask subTask) {
        subTasks.add(subTask.getTaskId());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
