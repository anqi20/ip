package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents class that handles the deadline command.
 */
public class DeadlineCommand extends Command {
    private final String userCommand;

    public DeadlineCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the adding of the deadline task.
     *
     * @param task taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList task, Storage storage) {
        int numberOfTasks = task.getSize();

        try {
            int dividerPosition = userCommand.indexOf(Constants.COMMAND_DEADLINE_BY);
            String userCommandName = userCommand.substring(Constants.LENGTH_OF_DEADLINE + 1, dividerPosition).trim();
            String userCommandBy = userCommand.substring(dividerPosition + Constants.LENGTH_OF_BY + 1).trim();

            Deadline d = new Deadline(userCommandName, userCommandBy);
            task.addTask(d);
            numberOfTasks++;
            Ui.printAddTask(d.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
            //Wrong formatting was given
            Ui.printFormattingInvalid();
        }

        super.execute(task, storage);

    }

}
