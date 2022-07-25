package utils;

import service.HistoryManager;
import service.TaskManager;
import service.implementation.InMemoryHistoryManager;
import service.implementation.InMemoryTaskManager;

public class  Managers {
    private static TaskManager taskManager = new InMemoryTaskManager();
    private static HistoryManager historyManager;

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
}
