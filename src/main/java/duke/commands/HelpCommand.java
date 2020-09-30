package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command{

    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printHelp();
    }

}
