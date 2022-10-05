package utils;

import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import service.HistoryManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        TaskType taskType = TaskType.valueOf(fromString[1]);
        if (TaskType.TASK.equals(taskType)) {
            LocalDateTime localDateTime = LocalDateTime.parse(fromString[5]);
            return new Task(fromString[2], fromString[4], Status.valueOf(fromString[3]),
                    TaskType.valueOf(fromString[1]), localDateTime,Long.parseLong(fromString[6]));
        }
        if (TaskType.SUBTASK.equals(taskType)) {
            return new SubTask(fromString[2], fromString[4], Status.valueOf(fromString[3].trim()),
                    TaskType.valueOf(fromString[1]), LocalDateTime.parse(fromString[5]), Long.parseLong(fromString[6]),
                    Integer.parseInt(fromString[7].trim()));
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
        for (String s : historyId) {
            history.add(Integer.parseInt(s));
        }
        return history;
    }

}
