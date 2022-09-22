package model;

import model.constants.TaskType;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTasks = new ArrayList<>();

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
