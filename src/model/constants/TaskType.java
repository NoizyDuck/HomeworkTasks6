package model.constants;

public enum TaskType {
    TASK, SUBTASK, EPIC;

    public static TaskType getFromString(String string) {
        if (TASK.toString().equalsIgnoreCase(string)) {
            return TASK;
        } else if (SUBTASK.toString().equalsIgnoreCase(string)) {
            return SUBTASK;
        } else if (EPIC.toString().equalsIgnoreCase(string)) {
            return EPIC;
        } else {
            System.out.println("Unsupported task type");
            return null;
        }
    }
}
