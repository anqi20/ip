package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList task, Storage storage) {
        storage.writeFile(task.getList());
    }
}
