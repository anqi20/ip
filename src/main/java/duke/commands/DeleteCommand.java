package duke.commands;

import duke.constants.Constants;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public String taskNumString;

    public DeleteCommand(String userCommand) {
        taskNumString = userCommand.substring(Constants.LENGTH_OF_DELETE);
    }

    @Override
    public void execute(TaskList task, Storage storage) {
        try{
            ArrayList<Task> entireList = task.getList();
            int numberOfTasks = task.getSize();
            int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

            Task t = entireList.get(taskNum);
            task.deleteTask(taskNum);
            numberOfTasks--;

            Ui.printDeleteTask(t.toString(), numberOfTasks);

        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            Ui.printFormattingInvalid(); //Wrong formatting was given

        } catch (IndexOutOfBoundsException e) {
            Ui.printNumberNotInRange(task.getList()); //Number task has exceeded the range
        }

        super.execute(task, storage);

    }

}
