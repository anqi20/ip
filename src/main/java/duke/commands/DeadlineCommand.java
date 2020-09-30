package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    String userCommand;

    public DeadlineCommand(String userCommand) {
        this.userCommand = userCommand;
    }

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
            Ui.printFormattingInvalid(); //Wrong formatting was given
        }

        super.execute(task, storage);

    }

}
