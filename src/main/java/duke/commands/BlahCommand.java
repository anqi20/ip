package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents class that handles the blah command.
 */
public class BlahCommand extends Command {

    /**
     * Executes the blah command.
     * Printing out the user interface of the blah command. 
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printBlah();
    }

}
