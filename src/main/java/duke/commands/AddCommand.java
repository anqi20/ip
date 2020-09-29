package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand {
    public Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(Storage storage, TaskList task, Ui ui) {
        task.addTask
    }

}
