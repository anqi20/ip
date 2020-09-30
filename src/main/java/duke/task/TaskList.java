package duke.task;

import java.util.ArrayList;

/**
 * Represents a taskList class.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task going to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task from the taskList.
     *
     * @param taskNum index of the task to be removed.
     */
    public void deleteTask(int taskNum) {
        taskList.remove(taskNum);
    }

    /**
     * Gets the entire taskList.
     *
     * @return the entire taskList which is an array list.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Gets the size of the taskList.
     *
     * @return number of tasks in the taskList which is an array list.
     */
    public int getSize() {
        return taskList.size();
    }
}
