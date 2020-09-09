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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] entireList = new Task[100];
        int counterList = 0;
        boolean isExit = false;
        boolean isInvalid = false;

        //Constants
        final int LENGTH_OF_BY = 3; // or LENGTH_OF_AT
        final int LENGTH_OF_DONE = 4;
        final int LENGTH_OF_TODO = 4;
        final int LENGTH_OF_EVENT = 5;
        final int LENGTH_OF_DEADLINE = 8;

        //Greetings
        Replies.printGreetings();

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            if (userCommand.equals("list")) {
                if(counterList > 0) {
                    Replies.printList(entireList);
                }

                else if(counterList == 0) {
                    Replies.printEmptyList();
                }
            }

            else if (userCommand.equals("blah")) {
                Replies.printBlah();
            }

            else if (userCommand.equals("bye")) {
                Replies.printBye();
                isExit = true;
            }

            else if (userCommand.startsWith("done")) {
                String taskNumString = userCommand.substring(LENGTH_OF_DONE);
                int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

                //Valid "done" command
                if((taskNum < counterList) && (taskNum >= 0)) {
                    entireList[taskNum].markAsDone();
                    Replies.printDoneValid(entireList, taskNum);
                }
                //Invalid "done" command
                else {
                    Replies.printDoneInvalid(entireList);
                }
            }

            else if(userCommand.startsWith("deadline")){

                //Valid "deadline" command
                if(userCommand.contains("/by")){
                    Deadline d = getDeadline(LENGTH_OF_BY, LENGTH_OF_DEADLINE, userCommand);
                    entireList[counterList] = d;
                    counterList++;

                    Replies.printToAddTask(d.toString(), counterList);
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

                    Replies.printToAddTask(e.toString(), counterList);
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

                Replies.printToAddTask(t.toString(), counterList);
            }

            else if(userCommand.equals("?")) {
                Replies.printUnsure();
            }

            else {
                Replies.printUnsure();
            }

            if(isInvalid) {
                Replies.printInvalid();
                isInvalid = false;
            }
        }
    }
}
