package duke.task;

import duke.constants.Constants;

/**
 * Represents a deadline class.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the format when deadline task is being written back into the text file.
     * e.g., [D][✓] return book (by: June 6th)
     *
     * @return String with task description and task date.
     */
    @Override
    public String toString() {
        return Constants.PRINT_DEADLINE + super.toString() + " " + Constants.PRINT_DEADLINE_BY + " " + by + ")";
    }
}
