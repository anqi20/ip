package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList task, Ui ui);

}
