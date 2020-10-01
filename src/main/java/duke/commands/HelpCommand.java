package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents class that handles the help command.
 */
public class HelpCommand extends Command{

    /**
     * Execute the help command.
     * Printing out the entire list of command that can be used by the user.
     *
     * @param taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printHelp();
    }

}
