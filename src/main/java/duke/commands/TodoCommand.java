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
    public void execute(TaskList task, Storage storage) {
        int numberOfTasks = task.getSize();

        try {
            String userCommandName = userCommand.substring(Constants.LENGTH_OF_TODO +1).trim();

            ToDos t = new ToDos(userCommandName);
            task.addTask(t);
            numberOfTasks++;
            Ui.printAddTask(t.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException e){
            Ui.printFormattingInvalid(); //Wrong formatting was given

        } catch (NullPointerException e) {
            Ui.printNumberNotInRange(task.getList()); //Number task has exceeded the range
        }

        super.execute(task, storage);
    }

}
