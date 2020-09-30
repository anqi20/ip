package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public String userCommand;

    public FindCommand(String userCommand) {
        this.userCommand = userCommand;
    }

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
