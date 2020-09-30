package duke.task;

import duke.constants.Constants;

/**
 * Represents a event class.
 */
public class Events extends Task {
    protected String at;

    public Events (String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the format when event task is being written back into the text file.
     * e.g., [E][✓] project meeting (at: Aug 6th 2-4pm)
     *
     * @return String with task description and task date.
     */
    @Override
    public String toString() {
        return Constants.PRINT_EVENT + super.toString() + " " + Constants.PRINT_EVENT_AT + " " + at + ")";
    }
}
