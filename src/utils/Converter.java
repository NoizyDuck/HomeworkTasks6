package utils;

import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import service.HistoryManager;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static String getHeader() {
        return "id,type,name,status,description,epic \n";
    }

    public static String getHistoryId(HistoryManager manager) {
        List<String> historyIdList = new ArrayList<>();
        for (Task line : manager.getHistory()) {
            historyIdList.add(line.getTaskId().toString());
        }
        String historyId = String.join(",", historyIdList);
        return historyId;
    }

    public static Task fromString(String line) {
        String[] fromString = line.split(",");
        TaskType taskType = TaskType.getFromString(fromString[1]);
        if (TaskType.TASK.equals(taskType)) {
            return new Task(fromString[2], fromString[4], Status.getFromString(fromString[3]),
                    TaskType.getFromString(fromString[1]));
        }
        if (TaskType.SUBTASK.equals(taskType)) {
            return new SubTask(fromString[2], fromString[4], Status.getFromString(fromString[3].trim()),
                    Integer.parseInt(fromString[5].trim()));
        }
        if (TaskType.EPIC.equals(taskType)) {
            System.out.println();
            return new Epic(fromString[2], fromString[4]);
        }
        return null;
    }

    public static List<Integer> createHistoryFromString(String line) {
        List<Integer> history = new ArrayList<>();
        String[] historyId = line.split(",");
        for (int i = 0; i < historyId.length; i++) {
            history.add(Integer.parseInt(historyId[i]));
        }
        return history;
    }

}
