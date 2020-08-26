public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //returns tick or X symbols
    public String getStatusIcon() {
        return(isDone? "\u2713" : "\u2718");
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
