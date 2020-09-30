package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a class that handles the bye command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    /**
     * Exit the program.
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printBye();
    }
}
