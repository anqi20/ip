import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.FileAlreadyExistsException;

public class Duke {
    public static ArrayList<Task> entireList = new ArrayList<>();
    public static int counterList = 0;
    public static boolean isExit = false;
    public static String filePath = "docs/duke.txt";
    public static String directoryPath = "docs";

    //Constants
    static final int LENGTH_OF_BY = 3; // or LENGTH_OF_AT
    static final int LENGTH_OF_DONE = 4;
    static final int LENGTH_OF_DELETE = 6;
    static final int LENGTH_OF_TODO = 4;
    static final int LENGTH_OF_EVENT = 5;
    static final int LENGTH_OF_DEADLINE = 8;
    static final int LENGTH_OF_INPUT_FORMAT = 6; //[✘][✓]
    static final int LENGTH_OF_INPUT_DONE_STATUS = 4;
    static final int TICK_HTML_CODE = 10003;

    public static void fileReading (String filePath) throws IOException {
        try {
            Files.createDirectories(Paths.get(directoryPath));
            Files.createFile(Path.of(filePath));

        } catch (FileAlreadyExistsException e) {
            //Ignore
        }


        File f = new File(filePath);
        Scanner s = new Scanner (f);

        while(s.hasNext()) {
            String line = s.nextLine();
            Integer taskDone = line.codePointAt(LENGTH_OF_INPUT_DONE_STATUS);

            if(line.startsWith("[T]")) {

                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1).trim();
                ToDos t = new ToDos(userCommandName);
                entireList.add(counterList, t);

            } else if (line.startsWith("[E]")) {

                int dividerPosition = line.indexOf("(at:");
                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + LENGTH_OF_BY +1, line.length()-1).trim();
                Events e = new Events(userCommandName, userCommandBy);
                entireList.add(counterList, e);

            } else if(line.startsWith("[D]")) {

                int dividerPosition = line.indexOf("(by:");
                String userCommandName = line.substring(LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + LENGTH_OF_BY +1, line.length()-1).trim();
                Deadline d = new Deadline(userCommandName, userCommandBy);
                entireList.add(counterList, d);

            } else {
                //Invalid task found
                Replies.printReadFileInvalid();
            }
            if (taskDone.equals(TICK_HTML_CODE)) {
                entireList.get(counterList).markAsDone();
            }
            counterList++;
        }
    }

    public static void readFile() {
        try {
            fileReading(filePath);

        } catch (IOException e) {
            Replies.printReadFileError(); //Input file has an error
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

        return new ToDos(userCommandName);
    }

    public static Events getEvents(String userCommand) {

        int dividerPosition = userCommand.indexOf("/at");
        String userCommandName = userCommand.substring(LENGTH_OF_EVENT +1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY +1).trim();

        return new Events(userCommandName, userCommandBy);
    }

    public static Deadline getDeadline(String userCommand) {

        int dividerPosition = userCommand.indexOf("/by");
        String userCommandName = userCommand.substring(LENGTH_OF_DEADLINE + 1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + LENGTH_OF_BY + 1).trim();

        return new Deadline(userCommandName, userCommandBy);
    }

    public static void markAsDone(String userCommand) throws DukeException {

        String taskNumString = userCommand.substring(LENGTH_OF_DONE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

        if(entireList.get(taskNum).isDone) {
            throw new DukeException();
        }
        entireList.get(taskNum).markAsDone();
        Replies.printDoneValid(entireList, taskNum);
    }

    public static void delete(String userCommand) {

        String taskNumString = userCommand.substring(LENGTH_OF_DELETE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

        Task t = entireList.get(taskNum);
        entireList.remove(taskNum);
        counterList--;
        Replies.printDelete(t.toString(), counterList);
    }

    public static int addTasks(String userCommand) throws DukeException {

        if(userCommand.toLowerCase().startsWith("deadline")) {

            if(!userCommand.contains("/by")) {
                throw new DukeException();
            }
            Deadline d = getDeadline(userCommand);
            entireList.add(counterList, d);
            counterList++;
            Replies.printToAddTask(d.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("event")) {

            if(!userCommand.contains("/at")) {
                throw new DukeException();
            }
            Events e = getEvents(userCommand);
            entireList.add(counterList, e);
            counterList++;
            Replies.printToAddTask(e.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith("todo")) {

            ToDos t = getToDos(userCommand);
            entireList.add(counterList, t);
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

            } catch (IndexOutOfBoundsException e) {
                Replies.printNotInRange(entireList); //Number task has exceeded the range
            }

        } else if (userCommand.toLowerCase().startsWith("delete")) {

            try {
                delete(userCommand);
                counterList = addTasks(userCommand);

            } catch (DukeException | StringIndexOutOfBoundsException e){
                Replies.printFormattingInvalid(); //Wrong formatting was given

            } catch (NumberFormatException e) {
                Replies.printFormattingInvalid(); //Number task was not given

            } catch (IndexOutOfBoundsException e) {
                Replies.printNotInRange(entireList); //Number task has exceeded the range
            }

        } else if(userCommand.equals("help")) {
            Replies.printHelp();

        } else {
            Replies.printHelp();
        }

        //Updating the file
        try {
            writeFile(filePath);

        } catch (IOException e) {
            Replies.printWriteFileError(); //Error when writing into the file
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