package service.implementation;

import model.Epic;
import model.SubTask;
import model.Task;
import model.constants.TaskType;
import model.exceptions.ManagerSaveException;
import service.HistoryManager;
import utils.Converter;
import utils.Managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTasksManager(File file) throws IOException {
        if (file.length() != 0) {
            loadDataFromFile(file);
        }
        this.file = file;
    }

    public void loadDataFromFile(File file) throws IOException {
        HistoryManager historyManager = Managers.getDefaultHistory();
        List<Integer> history = Collections.emptyList();
        try {


            String csv = Files.readString(Path.of(file.getPath()));
            String[] lines = csv.split("\n");
            ArrayList<String> epics = new ArrayList<>();
            ArrayList<String> tasks = new ArrayList<>();
            ArrayList<String> subtasks = new ArrayList<>();
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                if (!line.isEmpty()) {
                    TaskType type = TaskType.valueOf(line.split(",")[1]);
                    if (TaskType.TASK.equals(type)) {
                        tasks.add(line);
                    } else if (TaskType.EPIC.equals(type)) {
                        epics.add(line);
                    } else if (TaskType.SUBTASK.equals(type)) {
                        subtasks.add(line);
                    }
                } else {
                    history = Converter.createHistoryFromString(lines[i + 1]);
                    break;
                }
            }
            List<Integer> finalHistory = history;

            saveHistory(historyManager, tasks, finalHistory);
            saveHistory(historyManager, epics, finalHistory);
            saveHistory(historyManager, subtasks, finalHistory);

        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }

    }

    private void saveHistory(HistoryManager historyManager, ArrayList<String> tasks, List<Integer> finalHistory) {
        tasks.forEach(task -> {
            int id = Integer.parseInt(task.split(",")[0]);
            Task convertedTask = Converter.fromString(task);
            if (finalHistory.contains(id)) {
                historyManager.addTask(convertedTask);
            }
            addTask(convertedTask);
        });
    }

    private void addTask(Task task) {
        switch (task.getTaskType()) {
            case TASK:
                super.createTask(task);
                break;
            case EPIC:
                super.createEpic((Epic) task);
                break;
            case SUBTASK:
                super.createSubTask((SubTask) task);
        }
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (file.length() == 0) {
                writer.write(Converter.getHeader());
            }
            HistoryManager manager = Managers.getDefaultHistory();
            HashMap<Integer, Task> tasks = taskHashMap;
            HashMap<Integer, SubTask> subtasks = subTaskHashMap;
            HashMap<Integer, Epic> epics = epicTaskHashMap;
            for (Integer taskId : tasks.keySet()) {
                writer.write(tasks.get(taskId).toString() + "\n");
            }
            for (Integer subId : subtasks.keySet()) {
                writer.write(subtasks.get(subId).toString() + "\n");
            }
            for (Integer epicId : epics.keySet()) {
                writer.write(epics.get(epicId).toString() + "\n");
            }

            writer.write("\n");

            writer.write(Converter.getHistoryId(manager));

        } catch (Exception ex) {
            throw new ManagerSaveException(ex.getMessage());
        }
    }


    @Override
    public Task createTask(Task task) {
        Task newTask = super.createTask(task);
        save();
        return newTask;
    }

    @Override
    public Epic createEpic(Epic epic) {
        Epic newEpic = super.createEpic(epic);
        save();
        return newEpic;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        SubTask newSubtask = super.createSubTask(subTask);
        save();
        return newSubtask;
    }



    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteAllSubTasks() {
        super.deleteAllSubTasks();
        save();
    }

    @Override
    public String getTaskById(int id) {
        String task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public String getEpicById(int id) {
        String epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public String getSubTaskById(int id) {
        String subTask = super.getSubTaskById(id);
        save();
        return subTask;
    }

    @Override
    public void updateTask(int id, Task task) {
        super.updateTask(id, task);
        save();
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        super.updateEpic(id, epic);
        save();
    }

    @Override
    public void updateSubTask(int id, SubTask subTask) {
        super.updateSubTask(id, subTask);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubTaskById(int id) {
        super.deleteSubTaskById(id);
        save();
    }

}

