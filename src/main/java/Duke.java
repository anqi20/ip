import java.util.Scanner;

public class Duke {

    public static ToDos getToDos(int LENGTH_OF_TODO, String userCommand) {
        String userCommandName = userCommand.substring(LENGTH_OF_TODO +1).trim();

        ToDos t = new ToDos(userCommandName);
        return t;
    }

    public static Events getEvents(int LENGTH_OF_BY, int LENGTH_OF_EVENT, String userCommand) {
        int dividerPosition = userCommand.indexOf("/at");
        String userCommandName = userCommand.substring(LENGTH_OF_EVENT +1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY +1).trim();

        Events e = new Events(userCommandName, userCommandBy);
        return e;
    }

    public static Deadline getDeadline(int LENGTH_OF_BY, int LENGTH_OF_DEADLINE, String userCommand) {
        int dividerPosition = userCommand.indexOf("/by");
        String userCommandName = userCommand.substring(LENGTH_OF_DEADLINE +1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY +1).trim();

        Deadline d = new Deadline(userCommandName, userCommandBy);
        return d;
    }

    public static void printEntireList(Task[] list) {
        int i = 0;
        for(Task item: list) {
            if(item != null) {
                System.out.println((i+1) + ". "+ item.toString());
                i++;
            }
        }
    }

    public static void replyToAddTask(int counterList, String DASH_LINE, String s) {
        System.out.println(DASH_LINE);
        System.out.println("Got it! I've added this task: ");
        System.out.println(s);
        System.out.print("Now you have " + counterList + " task");
        if (counterList == 1) {
            System.out.print(" ");
        } else {
            System.out.print("s ");
        }
        System.out.println("in the list.");
        System.out.println(DASH_LINE);
    }

    public static void replyToList(Task[] entireList, String DASH_LINE) {
        System.out.println(DASH_LINE);
        System.out.println("Here is your list!");
        printEntireList(entireList);
        System.out.println(DASH_LINE);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] entireList = new Task[100];
        int counterList = 0;
        boolean isExit = false;
        boolean isInvalid = false;
        boolean isUnsure = false;

        //Constants
        final int LENGTH_OF_BY = 3; // or LENGTH_OF_AT
        final int LENGTH_OF_DONE = 4;
        final int LENGTH_OF_TODO = 4;
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_DEADLINE = 8;
        final String DASH_LINE = "--------------------------------------------------";
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);

        //Greetings
        System.out.println(DASH_LINE);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(DASH_LINE);

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            if (userCommand.equals("list")) {
                if(counterList > 0) {
                    replyToList(entireList, DASH_LINE);
                }

                else if(counterList == 0) {
                    System.out.println(DASH_LINE);
                    System.out.println("There is nothing on your list! Add something to begin! ");
                    System.out.println(DASH_LINE);
                }
            }

            else if (userCommand.equals("blah")) {
                System.out.println(DASH_LINE);
                System.out.println("Blah blah blah blah blah");
                System.out.println("Hahaha, kidding! What were you saying again?");
                System.out.println(DASH_LINE);
            }

            else if (userCommand.equals("bye")) {
                System.out.println(DASH_LINE);
                System.out.println("Bye bye. Hope you have a nice day and see you soon!");
                System.out.println(DASH_LINE);
                isExit = true;
            }

            else if (userCommand.startsWith("done")) {
                String taskNumString = userCommand.substring(LENGTH_OF_DONE);
                int taskNumInt = Integer.parseInt(taskNumString.trim()) - 1;

                //Valid "done" command
                if((taskNumInt < counterList) && (taskNumInt >= 0)) {
                    entireList[taskNumInt].markAsDone();
                    System.out.println(DASH_LINE);
                    System.out.println("Congratulations! You have completed: ");
                    System.out.println((taskNumInt + 1) + ". " + entireList[taskNumInt].toString());
                    System.out.println(DASH_LINE);
                }
                //Invalid "done" command
                else {
                    System.out.println(DASH_LINE);
                    System.out.println("Errr... I don't think you have this item on the list.");
                    System.out.println("This is the current list that you have:");
                    printEntireList(entireList);
                    System.out.println(DASH_LINE);
                }
            }

            else if(userCommand.startsWith("deadline")){

                //Valid "deadline" command
                if(userCommand.contains("/by")){
                    Deadline d = getDeadline(LENGTH_OF_BY, LENGTH_OF_DEADLINE, userCommand);
                    entireList[counterList] = d;
                    counterList++;

                    replyToAddTask(counterList, DASH_LINE, d.toString());
                }

                //Invalid "deadline" command
                else {
                    isInvalid = true;
                }
            }

            else if(userCommand.startsWith("event")) {

                //Valid "Events" command
                if(userCommand.contains("/at")){
                    Events e = getEvents(LENGTH_OF_BY, LENGTH_OF_EVENT, userCommand);
                    entireList[counterList] = e;
                    counterList++;

                    replyToAddTask(counterList, DASH_LINE, e.toString());
                }

                //Invalid "Events" command
                else {
                    isInvalid = true;
                }
            }

            else if(userCommand.startsWith("todo")) {
                ToDos t = getToDos(LENGTH_OF_TODO, userCommand);
                entireList[counterList] = t;
                counterList++;

                replyToAddTask(counterList, DASH_LINE, t.toString());
            }

            else if(userCommand.equals("?")) {
                isUnsure = true;
            }

            else {
                isUnsure = true;
            }

            if(isUnsure) {
                System.out.println(DASH_LINE);
                System.out.println("You must be confused! \u2719_\u2719");
                System.out.println("Here is the list of commands that I can understand: ");
                System.out.println("1) list\n2) blah\n3) todo...\n4) event... /at...\n5) deadline... /by...\n6) ?\n7)" +
                        " bye");
                System.out.println(DASH_LINE);
                isUnsure = false;
            }

            if(isInvalid) {
                System.out.println(DASH_LINE);
                System.out.println("Invalid command!");
                System.out.println("This is the formatting for the commands: ");
                System.out.println("    deadline <description> /by <date>");
                System.out.println("    event <description> /at <date>");
                System.out.println(DASH_LINE);
                isInvalid = false;
            }
        }
    }
}
