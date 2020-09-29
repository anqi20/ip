package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;

public class BlahCommand extends Command {

    public void execute(TaskList task, Ui ui) {
        Ui.printBlah();
    }

}
