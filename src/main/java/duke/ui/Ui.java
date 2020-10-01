package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Manages user interface of the application, contains messages that the user will see.
 */
public class Ui {

    /**
     * Prints a line of dashes that separates the different commands and the user's input.
     */
    public static void printDashLine(){
        System.out.println("--------------------------------------------------");
    }

    /**
     * Prints the welcome message when user first start the application.
     */
    public static void printGreetings() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        printDashLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printDashLine();
    }

    /**
     * Prints the message when users write blah.
     */
    public static void printBlah() {
        printDashLine();
        System.out.println("Blah blah blah blah blah");
        System.out.println("Hahaha, kidding! What were you saying again?");
        printDashLine();
    }

    /**
     * Prints the message when the users want to exit the program.
     */
    public static void printBye() {
        printDashLine();
        System.out.println("Bye bye. Hope you have a nice day and see you soon!");
        printDashLine();
    }

    /**
     * Prints the message when the user wants to see the list but the list is empty.
     */
    public static void printEmptyList() {
        printDashLine();
        System.out.println("There is nothing on your list! Add something to begin! ");
        printDashLine();
    }

    /**
     * Prints only the entire list.
     *
     * @param list entire list.
     */
    public static void printEntireList(ArrayList<Task> list) {
        int i = 1;
        for(Task item: list) {
            if(item != null) {
                System.out.println(i + ". " + item.toString());
                i++;
            }
        }
    }

    /**
     * Prints the entire list including the user interface.
     *
     * @param list entire list.
     */
    public static void printList(ArrayList<Task> list) {
        printDashLine();
        System.out.println("Here is your list!");
        printEntireList(list);
        printDashLine();
    }

    /**
     * Prints the message when the initially undone task is marked as done.
     *
     * @param list entire list.
     * @param taskNum index of the task that is marked as done.
     */
    public static void printDone(ArrayList<Task> list, int taskNum) {
        printDashLine();
        System.out.println("Congratulations! You have completed: ");
        System.out.println((taskNum + 1) + ". " + list.get(taskNum).toString());
        printDashLine();
    }

    /**
     * Prints the error message when the initially done task is marked as done again.
     *
     * @param list entire list.
     */
    public static void printDoneWhenDone(ArrayList<Task> list) {
        printDashLine();
        System.out.println("You have already completed this task!");
        System.out.println("This is the current list that you have: ");
        printEntireList(list);
        printDashLine();
    }

    /**
     * Prints the message when the task is deleted.
     *
     * @param s task that is going to be deleted.
     * @param counterList number of tasks in the list (excluding the one that is being removed) .
     */
    public static void printDeleteTask(String s, int counterList) {
        printDashLine();
        System.out.println("Okie! I've removed this task: ");
        System.out.println(s);
        System.out.print("Now you have " + counterList + " task");
        if(counterList <= 1) {
            System.out.print(" ");
        } else {
            System.out.print("s ");
        }
        System.out.println("in the list.");
        printDashLine();
    }

    /**
     * Prints the message when a new task is being added.
     *
     * @param s task that is going to be added in.
     * @param counterList number of tasks in the list (including the newly added one).
     */
    public static void printAddTask(String s, int counterList) {
        printDashLine();
        System.out.println("Got it! I've added this task: ");
        System.out.println(s);
        System.out.print("Now you have " + counterList + " task");
        if (counterList == 1) {
            System.out.print(" ");
        } else {
            System.out.print("s ");
        }
        System.out.println("in the list.");
        printDashLine();
    }

    /**
     * Prints the message when the user requests for help.
     * All the different commands can be seen here.
     */
    public static void printHelp() {
        printDashLine();
        System.out.println("You must be confused! \u2719_\u2719");
        System.out.println("Here is the list of commands that I can understand: ");
        System.out.println("1) list");
        System.out.println("2) blah");
        System.out.println("3) todo ...");
        System.out.println("4) event ... /at ...");
        System.out.println("5) deadline ... /by ...");
        System.out.println("6) done ...");
        System.out.println("7) delete ... ");
        System.out.println("8) find ...");
        System.out.println("9) help");
        System.out.println("10) bye");
        printDashLine();
    }

    /**
     * Prints the message when the user finds certain keywords.
     * Prints the list of tasks which contains the keywords.
     *
     * @param list of tasks which contains the keywords.
     */
    public static void printFind(ArrayList<Task> list) {
        printDashLine();
        System.out.println("Here are the matching tasks in your list: ");
        printEntireList(list);
        printDashLine();
    }

    /**
     * Prints the message when all the tasks do not contain the keywords.
     */
    public static void printFindNothing() {
        printDashLine();
        System.out.println("There is no matching task in your list. ");
        printDashLine();
    }

    /**
     * Prints the error message when there is problems with the formatting.
     */
    public static void printFormattingInvalid() {
        printDashLine();
        System.out.println("There's something wrong with your formatting!");
        System.out.println("If you are unsure, use \"help\" to check the formatting. ");
        printDashLine();
    }

    /**
     * Prints the error message when the index of task is not within the range of the number of tasks in the list.
     * Prints the entire list to remind user how many tasks and what are the tasks in the list.
     *
     * @param list entire list.
     */
    public static void printNumberNotInRange(ArrayList<Task> list) {
        printDashLine();
        System.out.println("Errr... I don't think you have this item on the list.");
        System.out.println("This is the current list that you have:");
        printEntireList(list);
        printDashLine();
    }

    /**
     * Prints the error message when there is problem reading the text file.
     */
    public static void printReadFileError() {
        printDashLine();
        System.out.println("An error has occurred when we try to read the file. ");
        System.out.println("Please check again! ");
        printDashLine();
    }

    /**
     * Prints the error message when task has invalid formatting when reading the text file.
     */
    public static void printReadFileInvalid() {
        printDashLine();
        System.out.println("Oops! Invalid task was detected!");
        printDashLine();
    }

    /**
     * Prints the error message when there is problem writing into the text file.
     */
    public static void printWriteFileError() {
        printDashLine();
        System.out.println("An error has occurred when we try to write into the file.");
        System.out.println("Please check again! ");
        printDashLine();
    }
}
