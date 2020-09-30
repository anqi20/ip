package duke.commands;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a class that handles the done command.
 */
public class DoneCommand extends Command {
    private final String taskNumString;

    public DoneCommand(String userCommand) {
        taskNumString = userCommand.substring(Constants.LENGTH_OF_DONE);
    }

    /**
     * Executes the method to mark the task as done.
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Task> entireList = taskList.getList();

        try {
            int taskNum = Integer.parseInt(taskNumString.trim()) - 1;
            if(entireList.get(taskNum).getStatus()) {
                throw new DukeException();
            }
            entireList.get(taskNum).markAsDone();
            Ui.printDone(entireList, taskNum);

        } catch (DukeException e) {
            //Task has already been completed
            Ui.printDoneWhenDone(entireList);

        } catch (NumberFormatException e) {
            //Number task was not given
            Ui.printFormattingInvalid();

        } catch (IndexOutOfBoundsException e) {
            //Number task has exceeded the range
            Ui.printNumberNotInRange(entireList);
        }

        super.execute(task, storage);
    }

}
