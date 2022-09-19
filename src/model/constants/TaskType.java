package model.constants;

public enum TaskType {
    TASK, SUBTASK, EPIC;

    public static TaskType getFromString(String string) {
        if (TASK.toString().equals(string)) {
            return TASK;
        } else if (SUBTASK.toString().equals(string)) {
            return SUBTASK;
        } else if (EPIC.toString().equals(string)) {
            return EPIC;
        } else {
            System.out.println("Unsupported task type");
            return null;
        }
    }
}
