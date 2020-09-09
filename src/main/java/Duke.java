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
        String userCommandName = userCommand.substring(LENGTH_OF_DEADLINE + 1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY + 1).trim();

        Deadline d = new Deadline(userCommandName, userCommandBy);
        return d;
    }

    public static void markAsDone(int LENGTH_OF_DONE, String userCommand, Task[] list) throws DukeException {

        String taskNumString = userCommand.substring(LENGTH_OF_DONE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;
        if(list[taskNum].isDone) {
            throw new DukeException();
        }
        list[taskNum].markAsDone();
        Replies.printDoneValid(list, taskNum);
    }

    public static int addTasks(String userCommand, Task[] list, int counterList, int LENGTH_OF_BY,
                               int LENGTH_OF_DEADLINE, int LENGTH_OF_EVENT, int LENGTH_OF_TODO)
            throws DukeException {

        if(userCommand.toLowerCase().startsWith("deadline")) {

            if(!userCommand.contains("/by")) {
                throw new DukeException();
            }
            Deadline d = getDeadline(LENGTH_OF_BY, LENGTH_OF_DEADLINE, userCommand);
            list[counterList] = d;
            counterList++;
            Replies.printToAddTask(d.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("event")) {

            if(!userCommand.contains("/at")) {
                throw new DukeException();
            }
            Events e = getEvents(LENGTH_OF_BY, LENGTH_OF_EVENT, userCommand);
            list[counterList] = e;
            counterList++;
            Replies.printToAddTask(e.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("todo")) {

            ToDos t = getToDos(LENGTH_OF_TODO, userCommand);
            list[counterList] = t;
            counterList++;
            Replies.printToAddTask(t.toString(), counterList);

        }

        return counterList;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] entireList = new Task[100];
        int counterList = 0;
        boolean isExit = false;

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

            if (userCommand.toLowerCase().equals("list")) {

                if(counterList == 0) {
                    Replies.printEmptyList();
                }
                Replies.printList(entireList);

            } else if (userCommand.toLowerCase().equals("blah")) {

                Replies.printBlah();

            } else if (userCommand.toLowerCase().equals("bye")) {

                Replies.printBye();
                isExit = true;

            } else if (userCommand.toLowerCase().startsWith("done")) {

                try {
                    markAsDone(LENGTH_OF_DONE, userCommand, entireList);
                } catch (DukeException e) {

                    //Task has already been completed
                    Replies.printDoneDone(entireList);
                } catch (NumberFormatException e) {

                    //Number task was not given
                    Replies.printFormattingInvalid();
                } catch (ArrayIndexOutOfBoundsException e) {

                    //Over the limit of 100 tasks
                    Replies.printOutOfRange();
                } catch (NullPointerException e) {

                    //Number task has exceeded the range
                    Replies.printDoneInvalid(entireList);
                }

            } else if (userCommand.toLowerCase().startsWith("deadline") | userCommand.toLowerCase().startsWith("event") |
                    userCommand.toLowerCase().startsWith("todo")) {

                try {
                    counterList = addTasks(userCommand, entireList, counterList, LENGTH_OF_BY, LENGTH_OF_DEADLINE,
                            LENGTH_OF_EVENT, LENGTH_OF_TODO);
                } catch (DukeException | StringIndexOutOfBoundsException e){

                    //Wrong formatting was given
                    Replies.printFormattingInvalid();
                } catch (ArrayIndexOutOfBoundsException e) {

                    //Over the limit of 100 tasks
                    Replies.printOutOfRange();
                } catch (NullPointerException e) {

                    //Number task has exceeded the range
                    Replies.printDoneInvalid(entireList);
                }
            } else if(userCommand.equals("?")) {

                Replies.printUnsure();

            } else {
                Replies.printUnsure();
            }
        }
    }
}