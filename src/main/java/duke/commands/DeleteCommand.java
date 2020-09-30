package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents class that handles the delete command.
 */
public class DeleteCommand extends Command {
    public final String taskNumString;

    public DeleteCommand(String userCommand) {
        taskNumString = userCommand.substring(Constants.LENGTH_OF_DELETE);
    }

    /**
     * Executes the deletion of a task.
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList task, Storage storage) {
        try{
            ArrayList<Task> entireList = task.getList();
            int numberOfTasks = task.getSize();
            int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

            Task t = entireList.get(taskNum);
            task.deleteTask(taskNum);
            numberOfTasks--;

            Ui.printDeleteTask(t.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            //Wrong formatting was given
            Ui.printFormattingInvalid();

        } catch (IndexOutOfBoundsException e) {
            //Number task has exceeded the range of the number of tasks in the taskList
            Ui.printNumberNotInRange(task.getList());
        }

        super.execute(task, storage);

    }

}
