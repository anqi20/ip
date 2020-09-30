package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public String userCommand;

    public TodoCommand(String userCommand) {
        this.userCommand = userCommand;
    }

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
            Ui.printFormattingInvalid(); //Wrong formatting was given

        } catch (NullPointerException e) {
            Ui.printNumberNotInRange(taskList.getList()); //Number task has exceeded the range
        }

        super.execute(taskList, storage);
    }

}
