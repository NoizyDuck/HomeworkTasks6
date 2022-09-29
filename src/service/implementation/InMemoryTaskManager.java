package service.implementation;

import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import service.Console;
import service.HistoryManager;
import service.TaskManager;
import utils.Managers;

import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {


    protected static Integer taskId = 0;
    final HashMap<Integer, Task> taskHashMap = new HashMap<>();
    final HashMap<Integer, Epic> epicTaskHashMap = new HashMap<>();
    final HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();
    // public static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    @Override
    public Task createTask(Task task) {
        if (!taskHashMap.containsValue(task)) {
            taskId++;
            task.setTaskId(taskId);
            taskHashMap.put(taskId, task);

            return task;
        } else {
            System.out.println("This task already exist");
            return null;
        }
    }

    @Override
    public Epic createEpic(Epic epic) {
        if (!epicTaskHashMap.containsValue(epic)) {
            taskId++;
            epic.setTaskId(taskId);
            epicTaskHashMap.put(taskId, epic);

            return epic;
        } else {
            System.out.println("This epic already exist");
            return null;
        }
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        if (!subTaskHashMap.containsValue(subTask)) {
            taskId++;
            subTask.setTaskId(taskId);
            subTaskHashMap.put(taskId, subTask);
            linkSubtaskToEpic(subTask);
            return subTask;
        } else {
            System.out.println("This subTask already exist");
            return null;
        }
    }

    @Override
    public String getTask(HashMap<Integer, Task> map) {
        StringBuilder result = new StringBuilder();
        for (Integer id : map.keySet()) {
            result.append("\nModel.Task Number ").append(id).append(": ").append(taskHashMap.get(id).toString());
        }
        return result.toString();
    }

//    @Override
//    public String getTasks() {
//        StringBuilder result = new StringBuilder();
//        for (Integer id : taskHashMap.keySet()) {
//            result.append("\nModel.Task Number ").append(id).append(": ").append(taskHashMap.get(id).toString());
//        }
//        return result.toString();
//    }
//
//    @Override
//    public String getEpics() {
//        StringBuilder result = new StringBuilder();
//        for (Integer id : epicTaskHashMap.keySet()) {
//            result.append("\nModel.Epic Number ").append(id).append(": ").append(epicTaskHashMap.get(id).toString());
//        }
//        return result.toString();
//    }
//
//    @Override
//    public String getSubTasks() {
//        StringBuilder result = new StringBuilder();
//        for (Integer id : subTaskHashMap.keySet()) {
//            result.append("\nModel.SubTask Number ").append(id).append(": ").append(subTaskHashMap.get(id).toString());
//        }
//        return result.toString();
//    }

    @Override
    public void deleteAllTasks() {
        taskHashMap.clear();
    }

    @Override
    public void deleteAllEpics() {
        epicTaskHashMap.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        subTaskHashMap.clear();
    }

    @Override
    public String getTaskById(int id) {
        StringBuilder result = new StringBuilder();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (taskHashMap.containsKey(id)) {
            result.append("\nModel.Task Number ").append(id).append(": ").append(taskHashMap.get(id).toString());
            inMemoryHistoryManager.addTask(taskHashMap.get(id));
            return result.toString();
        } else {
            Console.noTaskId();
            return null;
        }

    }

    @Override
    public String getEpicById(int id) {
        StringBuilder result = new StringBuilder();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (epicTaskHashMap.containsKey(id)) {
            result.append("\nModel.Epic Number ").append(id).append(": ").append(epicTaskHashMap.get(id).toString());
            inMemoryHistoryManager.addTask(epicTaskHashMap.get(id));
            return result.toString();

        } else {
            Console.noEpicId();
            return null;
        }
    }

    @Override
    public String getSubTaskById(int id) {
        StringBuilder result = new StringBuilder();
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (subTaskHashMap.containsKey(id)) {
            result.append("\nModel.SubTask Number " + id + ": " + subTaskHashMap.get(id).toString());
            inMemoryHistoryManager.addTask(subTaskHashMap.get(id));
            return result.toString();
        } else {
            Console.noSubId();
            return null;
        }
    }

    @Override
    public void updateTask(int id, Task task) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.put(id, task);
        } else {
            Console.noTaskId();
        }
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        if (epicTaskHashMap.containsKey(id)) {
            epicTaskHashMap.put(id, epic);
        } else {
            Console.noEpicId();
        }
    }

    @Override
    public void updateSubTask(int id, SubTask subTask) {
        if (subTaskHashMap.containsKey(id)) {
            subTaskHashMap.put(id, subTask);
            Epic epicToRecalculate = epicTaskHashMap.get(subTask.getEpicId());
            calculateEpicStatus(epicToRecalculate);
        } else {
            Console.noSubId();
        }
    }

    @Override
    public void deleteTaskById(int id) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
            inMemoryHistoryManager.remove(id);
        } else {
            Console.noTaskId();
        }
    }

    @Override
    public void deleteEpicById(int id) {
        if (epicTaskHashMap.containsKey(id)) {
            Epic epicToRemove = epicTaskHashMap.get(id);
            List<Integer> subTasksIds = epicToRemove.getSubTasksIds();
            for (Integer subTaskId : subTasksIds) {
                subTaskHashMap.remove(subTaskId);
                inMemoryHistoryManager.remove(subTaskId);
            }
            inMemoryHistoryManager.remove(id);
            epicTaskHashMap.remove(id);
        } else {
            Console.noEpicId();
        }
    }

    @Override
    public void deleteSubTaskById(int id) {
        if (subTaskHashMap.containsKey(id)) {
            subTaskHashMap.remove(id);
            inMemoryHistoryManager.remove(id);
        } else {
            Console.noSubId();
        }
    }

    @Override
    public String getSubTaskListByEpicId(int id) {
        StringBuilder result = new StringBuilder();
        if (epicTaskHashMap.containsKey(id)) {
            result.append("\nModel.Epic Number ").append(id).append(": ").append(epicTaskHashMap.get(id).toString());
            for (Epic epic : epicTaskHashMap.values()) {
                for (Integer subId : epic.getSubTasksIds()) {
                    result.append("\nModel.SubTask Number ").append(subId).append(": ").append(subTaskHashMap.get(subId).toString());
                }
            }
            return result.toString();
        } else {
            Console.noEpicId();
            return null;
        }
    }


    private void calculateEpicStatus(Epic epic) {
        Integer statusNew = 0;
        Integer statusDone = 0;
        List<Integer> subTasksIds = epic.getSubTasksIds();
        int size = subTasksIds.size();
        for (Integer subTaskId : subTasksIds) {
            SubTask subTask = subTaskHashMap.get(subTaskId);
            switch (subTask.getStatus()) {
                case NEW:
                    statusNew++;
                    break;
                case DONE:
                    statusDone++;
                    break;
            }
        }
        if (statusNew == size) {
            epic.setStatus(Status.NEW);
        } else if (statusDone == size) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    private void linkSubtaskToEpic(SubTask subTask) {
        Integer epicId = subTask.getEpicId();
        Epic epic = epicTaskHashMap.get(epicId);
        epic.linkSubtask(subTask);
        calculateEpicStatus(epic);
    }
}




