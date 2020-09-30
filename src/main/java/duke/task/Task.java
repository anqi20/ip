package duke.task;

/**
 * Represents a task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status symbol ✓ or ✘, depending on whether the task is done.
     *
     * @return ✓ if the task is done, ✘ if the task is not done.
     */
    public String getStatusIcon() {
        return(isDone? "\u2713" : "\u2718");
    }

    /**
     * Gets the status of the task on whether it is done or not.
     *
     * @return status of the task.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Returns the format when task is written back into the text file.
     * e.g., [✓] read book
     *
     * @return String with both the status symbol and description of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
