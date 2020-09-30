package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents command class.
 */
public abstract class Command {
    boolean isExit = false;

    /**
     * Checks if the program needs to be exited.
     *
     * @return boolean to check if the program needs to exit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Abstraction for executing the commands.
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    public void execute(TaskList task, Storage storage) {
        storage.writeFile(task.getList());
    }
}
