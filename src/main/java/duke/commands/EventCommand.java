package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Events;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents class that handles the event command.
 */
public class EventCommand extends Command {
    private final String userCommand;

    public EventCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the adding of the event task.
     *
     * @param taskList that contains the entire list of tasks.
     * @param storage storage of the tasks.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        int numberOfTasks = taskList.getSize();

        try {
            int dividerPosition = userCommand.indexOf(Constants.COMMAND_EVENT_AT);
            String userCommandDescription =
                    userCommand.substring(Constants.LENGTH_OF_EVENT +1, dividerPosition).trim();
            String userCommandBy = userCommand.substring(dividerPosition + Constants.LENGTH_OF_AT +1).trim();

            Events e = new Events(userCommandDescription, userCommandBy);
            taskList.addTask(e);
            numberOfTasks++;
            Ui.printAddTask(e.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
            //Wrong formatting was given
            Ui.printFormattingInvalid();
        }

        super.execute(taskList, storage);

    }

}
