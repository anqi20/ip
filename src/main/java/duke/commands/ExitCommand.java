package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    public void execute(TaskList task, Ui ui) {
        Ui.printBye();
    }
}
