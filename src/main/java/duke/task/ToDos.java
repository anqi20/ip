package duke.task;

import duke.constants.Constants;

/**
 * Represents a todo Task.
 */
public class ToDos extends Task {

    public ToDos (String description) {
        super(description);
    }

    /**
     * Returns the format when todo task is being written back into the text file.
     * e.g., [T][✓] read book
     *
     * @return String with the task description.
     */
    @Override
    public String toString() {
        return Constants.PRINT_TODO + super.toString();
    }
}
