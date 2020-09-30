package duke.commands;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DoneCommand extends Command {
    public String taskNumString;

    public DoneCommand(String userCommand) {
        taskNumString = userCommand.substring(Constants.LENGTH_OF_DONE);

    }

    @Override
    public void execute(TaskList task, Storage storage) {
        ArrayList<Task> entireList = task.getList();

        try {
            int taskNum = Integer.parseInt(taskNumString.trim()) - 1;
            if(entireList.get(taskNum).getStatus()) {
                throw new DukeException();
            }
            entireList.get(taskNum).markAsDone();
            Ui.printDone(entireList, taskNum);

        } catch (DukeException e) {
            Ui.printDoneWhenDone(entireList); //Task has already been completed

        } catch (NumberFormatException e) {
            Ui.printFormattingInvalid(); //Number task was not given

        } catch (IndexOutOfBoundsException e) {
            Ui.printNumberNotInRange(entireList); //Number task has exceeded the range
        }


        super.execute(task, storage);

    }

}
