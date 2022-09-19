package utils;

import java.util.List;
import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;

public class Converter {
    public static Task convert(List<String> string, TaskType taskType) {
        switch (taskType) {
            case SUBTASK:
                SubTask subTask = new SubTask(string.get(1), string.get(4), Status.getFromString(string.get(3)),
                        Integer.parseInt(string.get(5)));
                subTask.setTaskId(Integer.parseInt(string.get(0)));
                return subTask;
            case TASK:
                Task task = new Task(string.get(1), string.get(4), Status.getFromString(string.get(3)));
                task.setTaskId(Integer.parseInt(string.get(0)));
                return task;
            case EPIC:
                Epic epic = new Epic(string.get(1), string.get(4));
                epic.setTaskId(Integer.parseInt(string.get(0)));
                return epic;
            default:
                System.out.println("Unsupported");
        }
        return null;
    }
}
