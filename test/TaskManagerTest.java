import org.junit.jupiter.api.BeforeEach;
import service.TaskManager;
import java.io.IOException;
  abstract class TaskManagerTest <T extends TaskManager> {
    T manager;
    protected abstract T createManager() throws IOException;

      @BeforeEach
      void getManager() throws IOException {
          manager = createManager();
      }

    abstract void createTask();
    abstract void createEpic();

   abstract void createSubTask();
    abstract void getTask();

    abstract void deleteAllTasks();

    abstract void deleteAllEpics();

    abstract void deleteAllSubTasks();

    abstract void getTaskById();

    abstract void getEpicById();

    abstract void getSubTaskById();

    abstract void updateTask();

    abstract void updateEpic();

    abstract void updateSubTask();

    abstract void deleteTaskById();

    abstract void deleteEpicById();

    abstract void deleteSubTaskById();

}

