package service;

public class Console {
    public static final String NO_TASK_ID = "No such Model.Task Id";
    public static final String NO_EPIC_ID = "No such Model.Epic Id";
    public static final String NO_SUB_ID = "No such Model.SubTask Id";
    public static void noTaskId(){
        System.out.println(NO_TASK_ID);
    }

    public static void noEpicId(){
        System.out.println(NO_EPIC_ID);
    }

    public static void noSubId(){
        System.out.println(NO_SUB_ID);
    }
}
