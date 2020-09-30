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
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList task, Storage storage) {
        int numberOfTasks = task.getSize();

        try {
            String userCommandName = userCommand.substring(Constants.LENGTH_OF_TODO +1).trim();

            ToDos t = new ToDos(userCommandName);
            task.addTask(t);
            numberOfTasks++;
            Ui.printAddTask(t.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
            //Wrong formatting was given
            Ui.printFormattingInvalid();
        }

        super.execute(task, storage);
    }

}
