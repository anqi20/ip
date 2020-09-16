import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static Task[] entireList = new Task[100];
    public static int counterList = 0;
    public static boolean isExit = false;
    public static String filePath = "docs/duke.txt";

    //Constants
    static final int LENGTH_OF_BY = 3; // or LENGTH_OF_AT
    static final int LENGTH_OF_DONE = 4;
    static final int LENGTH_OF_TODO = 4;
    static final int LENGTH_OF_EVENT = 5;
    static final int LENGTH_OF_DEADLINE = 8;
    static final int LENGTH_OF_INPUT_FORMAT = 6; //[✘][✓]

    public static void fileReading (String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner (f);

        while(s.hasNext()) {
            String line = s.nextLine();

            if(line.startsWith("[T]")) {

                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1).trim();
                ToDos t = new ToDos(userCommandName);
                entireList[counterList] = t;
                counterList++;

            } else if (line.startsWith("[E]")) {

                int dividerPosition = line.indexOf("(at:");
                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + LENGTH_OF_BY +1).trim();
                Events e = new Events(userCommandName, userCommandBy);
                entireList[counterList] = e;
                counterList++;

            } else if(line.startsWith("[D]")) {

                int dividerPosition = line.indexOf("(by:");
                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + LENGTH_OF_BY +1).trim();
                Deadline d = new Deadline(userCommandName, userCommandBy);
                entireList[counterList] = d;
                counterList++;

            } else {
                //Invalid task found
                Replies.printReadFileInvalid();
            }
        }
    }

    public static void readFile() {
        try {
            fileReading(filePath);
        } catch (FileNotFoundException | DukeException e) {
            Replies.printReadFileError();
        }
    }

    public static void writeFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task item : entireList){
            if(item != null) {
                fw.write(item.toString() + System.lineSeparator());
            }
        }
        fw.close();
    }

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

        } else if (userCommand.toLowerCase().startsWith("done")) {

            try {
                markAsDone(userCommand);
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
                counterList = addTasks(userCommand);
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

        try {
            writeFile(filePath);
        } catch (IOException e) {
            System.out.println("GG man JY!");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;

        //Greetings
        Replies.printGreetings();

        readFile();

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            loop(userCommand);

        }
    }
}