package duke.commands;

import duke.constants.Constants;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public int taskNum;

    public DeleteCommand(String userCommand) {
        String taskNumString = userCommand.substring(Constants.LENGTH_OF_DELETE);
        taskNum = Integer.parseInt(taskNumString.trim()) - 1;
    }

    public void execute(TaskList task, Ui ui) {
        ArrayList<Task> entireList = task.getList();
        int numberOfTasks = task.getSize();

        Task t = entireList.get(taskNum);
        task.deleteTask(taskNum);
        numberOfTasks--;

        Ui.printDeleteTask(t.toString(), numberOfTasks);
    }

}
