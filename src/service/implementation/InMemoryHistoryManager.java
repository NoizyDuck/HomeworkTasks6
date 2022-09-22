package service.implementation;

import model.Node;
import model.Task;
import service.HistoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager {
    private Node<Task> first;
    private Node<Task> last;


    private HashMap<Integer, Node<Task>> map = new HashMap<>();

    public Node<Task> linkLast(Task task) {
        Node<Task> tempLast = last;
        Node<Task> newNode = new Node<>(tempLast, task, null);
        last = newNode;
        if (tempLast == null)
            first = newNode;
        else
            tempLast.setNext(newNode);


        return newNode;
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Integer key : map.keySet()) {
            tasks.add(map.get(key).getTask());
        }
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        if (map.containsKey(task.getTaskId())) {
            remove(task.getTaskId());
        }
        Node<Task> taskNode = linkLast(task);
        map.put(task.getTaskId(), taskNode);

    }

    @Override
    public void remove(Integer id) {
        Node<Task> nodeToRemove = map.get(id);
        if (Objects.nonNull(nodeToRemove)) {
            Node<Task> prev = nodeToRemove.getPrev();
            Node<Task> next = nodeToRemove.getNext();
            if (Objects.nonNull(prev)) {
                prev.setNext(next);
                if (Objects.isNull(next)) {
                    last = prev;
                }
            }
            if (Objects.nonNull(next)) {
                next.setPrev(prev);
                if (Objects.isNull(prev)) {
                    first = next;
                }
            }
            map.remove(id);
        }
    }

}
