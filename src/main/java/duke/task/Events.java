package duke.task;

import duke.constants.Constants;

public class Events extends Task {
    protected String at;

    public Events (String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return Constants.PRINT_EVENT + super.toString() + " " + Constants.PRINT_EVENT_AT + " " + at + ")";
    }
}
