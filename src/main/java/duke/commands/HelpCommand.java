package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command{

    @Override
    public void execute(TaskList task, Storage storage) {
        Ui.printHelp();
    }

}
