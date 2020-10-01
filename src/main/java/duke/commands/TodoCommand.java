package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.ui.Ui;

/**
 * Represents class that handles the todo command.
 */
public class TodoCommand extends Command {
    private final String userCommand;

    public TodoCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the adding of the todo task.
     *
     * @param taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getSize();

        try {
            String userCommandDescription = userCommand.substring(Constants.LENGTH_OF_TODO +1).trim();

            ToDos t = new ToDos(userCommandDescription);
            taskList.addTask(t);
            numberOfTasks++;
            Ui.printAddTask(t.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
            //Wrong formatting was given
            Ui.printFormattingInvalid();
        }

        super.execute(taskList, storage);
    }

}
