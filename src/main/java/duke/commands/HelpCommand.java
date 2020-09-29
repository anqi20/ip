package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command{

    public void execute(TaskList task, Ui ui) {
        Ui.printHelp();
    }

}
