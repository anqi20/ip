package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents class that handles the list command.
 */
public class ListCommand extends Command {

    /**
     * Executes the listing of the entire list stored in the text file.
     * Prints out the entire taskList.
     *
     * @param taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getSize();
        ArrayList<Task> entireList = taskList.getList();

        if(numberOfTasks == 0) {
            Ui.printEmptyList();
        } else {
            Ui.printList(entireList);
        }

        super.execute(taskList, storage);
    }

}
