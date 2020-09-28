package duke.task;

import duke.constants.Constants;

public class ToDos extends Task {

    public ToDos (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return Constants.PRINT_TODO + super.toString();
    }
}
