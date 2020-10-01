package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents class that handles the find command.
 */
public class FindCommand extends Command {
    public String userCommand;

    public FindCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the find command to search for keywords in the entire list.
     * Returns those tasks that contain the keywords (if any).
     *
     * @param taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getSize();
        ArrayList<Task> currentList = new ArrayList<>();

        try {
            String userCommandDescription = userCommand.substring(Constants.LENGTH_OF_FIND+1).trim();

            for(int i=0; i<numberOfTasks; i++) {
                Task task = taskList.get(i);

                if(task.getDescription().contains(userCommandDescription)) {
                    currentList.add(task);
                }
            }
            Ui.printFind(currentList);

        } catch (StringIndexOutOfBoundsException e) {
            Ui.printFormattingInvalid(); //Wrong formatting was given
        }
    }

}
