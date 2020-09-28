package duke.task;

import duke.constants.Constants;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return Constants.PRINT_DEADLINE + super.toString() + " " + Constants.PRINT_DEADLINE_BY + " " + by + ")";
    }
}
