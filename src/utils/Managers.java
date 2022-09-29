package utils;

import service.HistoryManager;
import service.TaskManager;
import service.implementation.FileBackedTasksManager;
import service.implementation.InMemoryHistoryManager;
import service.implementation.InMemoryTaskManager;

import java.io.File;
import java.io.IOException;

public class Managers {
    private static TaskManager taskManager = new InMemoryTaskManager();
    private static HistoryManager historyManager;
    private static FileBackedTasksManager fileBackedTasksManager;

    public static TaskManager getDefault() {
        if (taskManager == null) {
            taskManager = new InMemoryTaskManager();
            return taskManager;
        }
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        if (historyManager == null) {
            historyManager = new InMemoryHistoryManager();
            return historyManager;
        }
        return historyManager;
    }

    public static FileBackedTasksManager getDefaultFileBackedTasksManager(File file) throws IOException {
        if (fileBackedTasksManager == null) {
            fileBackedTasksManager = new FileBackedTasksManager(file);
            return fileBackedTasksManager;
        }
        return fileBackedTasksManager;
    }
}
