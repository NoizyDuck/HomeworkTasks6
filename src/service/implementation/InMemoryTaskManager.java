package service.implementation;

import java.util.Arrays;
import java.util.regex.Pattern;
import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.Status;
import model.constants.TaskType;
import service.Console;
import service.HistoryManager;
import service.TaskManager;
import java.util.HashMap;
import java.util.List;
import utils.Converter;
import utils.Managers;

import static model.constants.TaskType.EPIC;
import static model.constants.TaskType.TASK;
import static model.constants.TaskType.SUBTASK;


public class InMemoryTaskManager implements TaskManager {


    public static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    protected static Integer taskId = 0;
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicTaskHashMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();

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
    public String getTasks() {
        String result = null;
        for (Integer id : taskHashMap.keySet()) {
            result += "\nModel.Task Number " + id + ": " + taskHashMap.get(id).toString();
        }
        return result;
    }

    @Override
    public String getEpics() {
        String result = null;
        for (Integer id : epicTaskHashMap.keySet()) {
            result += "\nModel.Epic Number " + id + ": " + epicTaskHashMap.get(id).toString();
        }
        return result;
    }

    @Override
    public String getSubTasks() {
        String result = null;
        for (Integer id : subTaskHashMap.keySet()) {
            result += "\nModel.SubTask Number " + id + ": " + subTaskHashMap.get(id).toString();
        }
        return result;
    }

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
        String result;
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (taskHashMap.containsKey(id)) {
            result = "\nModel.Task Number " + id + ": " + taskHashMap.get(id).toString();
            inMemoryHistoryManager.addTask(taskHashMap.get(id));
            return result;
        } else {
            Console.noTaskId();
            return null;
        }

    }

    @Override
    public String getEpicById(int id) {
        String result;
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (epicTaskHashMap.containsKey(id)) {
            result = "\nModel.Epic Number " + id + ": " + epicTaskHashMap.get(id).toString();
            inMemoryHistoryManager.addTask(epicTaskHashMap.get(id));
            return result;

        } else {
            Console.noEpicId();
            return null;
        }
    }

    @Override
    public String getSubTaskById(int id) {
        String result = null;
        HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
        if (subTaskHashMap.containsKey(id)) {
            result = "\nModel.SubTask Number " + id + ": " + subTaskHashMap.get(id).toString();
            inMemoryHistoryManager.addTask(subTaskHashMap.get(id));
            return result;
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
    public void importData(List<String> readResult) {
        readResult.forEach(line -> {
            List<String> columns = Arrays.asList(line.split(","));
            if (isNumeric(columns.get(0))) {
                String taskType = columns.get(1);
                if (TASK.toString().equals(taskType)) {
                    Task task = Converter.convert(columns, TaskType.getFromString(taskType));
                    taskHashMap.put(task.getTaskId(), task);
                } else if (EPIC.toString().equals(taskType)) {
                    Epic epic = (Epic) Converter.convert(columns, TaskType.getFromString(taskType));
                    calculateEpicStatus(epic);
                    epicTaskHashMap.put(epic.getTaskId(), epic);
                } else if (SUBTASK.toString().equals(taskType)) {
                    SubTask subTask =(SubTask) Converter.convert(columns, TaskType.getFromString(taskType));
                    subTaskHashMap.put(subTask.getTaskId(), subTask);
                }
            }
        });
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(str).matches();
    }

    @Override
    public String getSubTaskListByEpicId(int id) {
        String result = null;
        if (epicTaskHashMap.containsKey(id)) {
            result = "\nModel.Epic Number " + id + ": " + epicTaskHashMap.get(id).toString();
            for (Epic epic : epicTaskHashMap.values()) {
                for (Integer subId : epic.getSubTasksIds()) {
                    result += "\nModel.SubTask Number " + subId + ": " + subTaskHashMap.get(subId).toString();
                }
            }
            return result;
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






