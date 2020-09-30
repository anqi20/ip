package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Events;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    String userCommand;

    public EventCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    public void execute(TaskList task, Storage storage) {
        int numberOfTasks = task.getSize();

        try {
            int dividerPosition = userCommand.indexOf(Constants.COMMAND_EVENT_AT);
            String userCommandName = userCommand.substring(Constants.LENGTH_OF_EVENT +1, dividerPosition).trim();
            String userCommandBy = userCommand.substring(dividerPosition + Constants.LENGTH_OF_BY +1).trim();

            Events e = new Events(userCommandName, userCommandBy);
            task.addTask(e);
            numberOfTasks++;
            Ui.printAddTask(e.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
                Ui.printFormattingInvalid(); //Wrong formatting was given
        }

        super.execute(task, storage);

    }

}
