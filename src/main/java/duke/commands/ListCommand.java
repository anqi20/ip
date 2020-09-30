package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getSize();
        ArrayList<Task> entireList = taskList.getList();

        if(numberOfTasks == 0) {
            Ui.printEmptyList();
        } else {
            Ui.printCurrentList(entireList);
        }

        super.execute(taskList, storage);
    }

}
