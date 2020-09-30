package duke.task;

import java.util.ArrayList;

public class TaskList {
    public final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNum) {
        taskList.remove(taskNum);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getSize() {
        return taskList.size();
    }
}
