package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList task, Storage storage) {
        int numberOfTasks = task.getSize();
        ArrayList<Task> entireList = task.getList();

        if(numberOfTasks == 0) {
            Ui.printEmptyList();
        } else {
            Ui.printCurrentList(entireList);
        }

        super.execute(task, storage);
    }

}
