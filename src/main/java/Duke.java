import java.util.Scanner;

public class Duke {
    public static Task[] entireList = new Task[100];
    public static int counterList = 0;
    public static boolean isExit = false;

    //Constants
    static final int LENGTH_OF_BY = 3; // or LENGTH_OF_AT
    static final int LENGTH_OF_DONE = 4;
    static final int LENGTH_OF_DELETE = 6;
    static final int LENGTH_OF_TODO = 4;
    static final int LENGTH_OF_EVENT = 5;
    static final int LENGTH_OF_DEADLINE = 8;

    public static ToDos getToDos(String userCommand) {

        String userCommandName = userCommand.substring(LENGTH_OF_TODO +1).trim();

        ToDos t = new ToDos(userCommandName);
        return t;
    }

    public static Events getEvents(String userCommand) {

        int dividerPosition = userCommand.indexOf("/at");
        String userCommandName = userCommand.substring(LENGTH_OF_EVENT +1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY +1).trim();

        Events e = new Events(userCommandName, userCommandBy);
        return e;
    }

    public static Deadline getDeadline(String userCommand) {

        int dividerPosition = userCommand.indexOf("/by");
        String userCommandName = userCommand.substring(LENGTH_OF_DEADLINE + 1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY + 1).trim();

        Deadline d = new Deadline(userCommandName, userCommandBy);
        return d;
    }

    public static void markAsDone(String userCommand) throws DukeException {

        String taskNumString = userCommand.substring(LENGTH_OF_DONE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;
        if(entireList[taskNum].isDone) {
            throw new DukeException();
        }
        entireList[taskNum].markAsDone();
        Replies.printDoneValid(entireList, taskNum);
    }

    public static void delete(String userCommand) {

        String taskNumString = userCommand.substring(LENGTH_OF_DELETE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

        Task t = entireList[taskNum];
        for(int i=taskNum; i<=counterList; i++) {
            entireList[i] = entireList[i+1];
        }
        entireList[counterList] = null;
        counterList--;

        Replies.printDelete(t.toString(), counterList);

    }

    public static int addTasks(String userCommand) throws DukeException {

        if(userCommand.toLowerCase().startsWith("deadline")) {

            if(!userCommand.contains("/by")) {
                throw new DukeException();
            }
            Deadline d = getDeadline(userCommand);
            entireList[counterList] = d;
            counterList++;
            Replies.printToAddTask(d.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("event")) {

            if(!userCommand.contains("/at")) {
                throw new DukeException();
            }
            Events e = getEvents(userCommand);
            entireList[counterList] = e;
            counterList++;
            Replies.printToAddTask(e.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("todo")) {

            ToDos t = getToDos(userCommand);
            entireList[counterList] = t;
            counterList++;
            Replies.printToAddTask(t.toString(), counterList);

        }

        return counterList;
    }

    public static void loop(String userCommand) {
        if (userCommand.toLowerCase().equals("list")) {

            if(counterList == 0) {
                Replies.printEmptyList();
            } else {
                Replies.printList(entireList);
            }

        } else if (userCommand.toLowerCase().equals("blah")) {

            Replies.printBlah();

        } else if (userCommand.toLowerCase().equals("bye")) {

            Replies.printBye();
            isExit = true;

        } else if (userCommand.toLowerCase().startsWith("deadline") | userCommand.toLowerCase().startsWith("event") |
                userCommand.toLowerCase().startsWith("todo")) {

            try {
                counterList = addTasks(userCommand);
                
            } catch (DukeException | StringIndexOutOfBoundsException e){
                Replies.printFormattingInvalid(); //Wrong formatting was given

            } catch (ArrayIndexOutOfBoundsException e) {
                Replies.printOutOfRange(); //Over the limit of 100 tasks

            } catch (NullPointerException e) {
                Replies.printNotInRange(entireList); //Number task has exceeded the range
            }

        } else if (userCommand.toLowerCase().startsWith("done")) {

            try {
                markAsDone(userCommand);

            } catch (DukeException e) {
                Replies.printDoneDone(entireList); //Task has already been completed

            } catch (NumberFormatException e) {
                Replies.printFormattingInvalid(); //Number task was not given

            } catch (ArrayIndexOutOfBoundsException e) {
                Replies.printOutOfRange(); //Over the limit of 100 tasks

            } catch (NullPointerException e) {
                Replies.printNotInRange(entireList); //Number task has exceeded the range
            }

        } else if (userCommand.toLowerCase().startsWith("delete")) {

            try {
                delete(userCommand);

            } catch (NumberFormatException e) {
                Replies.printFormattingInvalid(); //Number task was not given

            } catch (NullPointerException e) {
                Replies.printNotInRange(entireList); //Number task has exceeded the range
            }

        } else if(userCommand.equals("?")) {

            Replies.printUnsure();

        } else {
            Replies.printUnsure();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;

        //Greetings
        Replies.printGreetings();

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            loop(userCommand);

        }
    }
}