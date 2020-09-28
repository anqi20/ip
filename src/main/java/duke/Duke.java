package duke;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.Ui;

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
    public static final String filePath = "data/duke.txt";
    public static final String directoryPath = "data";

    public static void fileReading (String filePath) throws IOException {
        try {
            Files.createDirectories(Paths.get(directoryPath));
            Files.createFile(Path.of(filePath));

        } catch (FileAlreadyExistsException e) {
            //As the file already exists in the Directory, there is no need to create another file again
        }


        File f = new File(filePath);
        Scanner s = new Scanner (f);

        while(s.hasNext()) {
            String line = s.nextLine();
            Integer taskDone = line.codePointAt(Constants.LENGTH_OF_INPUT_DONE_STATUS);

            if(line.startsWith(Constants.PRINT_TODO)) {

                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1).trim();
                ToDos t = new ToDos(userCommandName);
                entireList.add(counterList, t);

            } else if (line.startsWith(Constants.PRINT_EVENT)) {

                int dividerPosition = line.indexOf(Constants.PRINT_EVENT_AT);
                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + Constants.LENGTH_OF_BY +1, line.length()-1).trim();
                Events e = new Events(userCommandName, userCommandBy);
                entireList.add(counterList, e);

            } else if(line.startsWith(Constants.PRINT_DEADLINE)) {

                int dividerPosition = line.indexOf(Constants.PRINT_DEADLINE_BY);
                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + Constants.LENGTH_OF_BY +1, line.length()-1).trim();
                Deadline d = new Deadline(userCommandName, userCommandBy);
                entireList.add(counterList, d);

            } else {
                //Invalid task found
                Ui.printReadFileInvalid();
            }
            if (taskDone.equals(Constants.TICK_HTML_CODE)) {
                entireList.get(counterList).markAsDone();
            }
            counterList++;
        }
    }

    public static void readFile() {
        try {
            fileReading(filePath);

        } catch (IOException e) {
            Ui.printReadFileError(); //Input file has an error
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

        String userCommandName = userCommand.substring(Constants.LENGTH_OF_TODO +1).trim();

        return new ToDos(userCommandName);
    }

    public static Events getEvents(String userCommand) {

        int dividerPosition = userCommand.indexOf(Constants.COMMAND_EVENT_AT);
        String userCommandName = userCommand.substring(Constants.LENGTH_OF_EVENT +1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + Constants.LENGTH_OF_BY +1).trim();

        return new Events(userCommandName, userCommandBy);
    }

    public static Deadline getDeadline(String userCommand) {

        int dividerPosition = userCommand.indexOf(Constants.COMMAND_DEADLINE_BY);
        String userCommandName = userCommand.substring(Constants.LENGTH_OF_DEADLINE + 1, dividerPosition).trim();
        String userCommandBy = userCommand.substring(dividerPosition + Constants.LENGTH_OF_BY + 1).trim();

        return new Deadline(userCommandName, userCommandBy);
    }

    public static void markAsDone(String userCommand) throws DukeException {

        String taskNumString = userCommand.substring(Constants.LENGTH_OF_DONE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

        if(entireList.get(taskNum).getStatus()) {
            throw new DukeException();
        }
        entireList.get(taskNum).markAsDone();
        Ui.printDone(entireList, taskNum);
    }

    public static void delete(String userCommand) {

        String taskNumString = userCommand.substring(Constants.LENGTH_OF_DELETE);
        int taskNum = Integer.parseInt(taskNumString.trim()) - 1;

        Task t = entireList.get(taskNum);
        entireList.remove(taskNum);
        counterList--;
        Ui.printDeleteTask(t.toString(), counterList);
    }

    public static int addTasks(String userCommand) throws DukeException {

        if(userCommand.toLowerCase().startsWith(Constants.COMMAND_DEADLINE)) {

            if(!userCommand.contains(Constants.COMMAND_DEADLINE_BY)) {
                throw new DukeException();
            }
            Deadline d = getDeadline(userCommand);
            entireList.add(counterList, d);
            counterList++;
            Ui.printAddTask(d.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_EVENT)) {

            if(!userCommand.contains(Constants.COMMAND_EVENT_AT)) {
                throw new DukeException();
            }
            Events e = getEvents(userCommand);
            entireList.add(counterList, e);
            counterList++;
            Ui.printAddTask(e.toString(), counterList);

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_TODO)) {

            ToDos t = getToDos(userCommand);
            entireList.add(counterList, t);
            counterList++;
            Ui.printAddTask(t.toString(), counterList);

        }
        return counterList;
    }

    public static void run(String userCommand) {
        if (userCommand.toLowerCase().equals(Constants.COMMAND_LIST)) {

            if(counterList == 0) {
                Ui.printEmptyList();
            } else {
                Ui.printCurrentList(entireList);
            }

        } else if (userCommand.toLowerCase().equals(Constants.COMMAND_BLAH)) {
            Ui.printBlah();

        } else if (userCommand.toLowerCase().equals(Constants.COMMAND_BYE)) {
            Ui.printBye();
            isExit = true;

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_DEADLINE) |
                userCommand.toLowerCase().startsWith(Constants.COMMAND_EVENT) |
                userCommand.toLowerCase().startsWith(Constants.COMMAND_TODO)) {

            try {
                counterList = addTasks(userCommand);

            } catch (DukeException | StringIndexOutOfBoundsException e){
                Ui.printFormattingInvalid(); //Wrong formatting was given

            } catch (NullPointerException e) {
                Ui.printNumberNotInRange(entireList); //Number task has exceeded the range
            }

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_DONE)) {

            try {
                markAsDone(userCommand);

            } catch (DukeException e) {
                Ui.printDoneWhenDone(entireList); //duke.task.Task has already been completed

            } catch (NumberFormatException e) {
                Ui.printFormattingInvalid(); //Number task was not given

            } catch (IndexOutOfBoundsException e) {
                Ui.printNumberNotInRange(entireList); //Number task has exceeded the range
            }

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_DELETE)) {

            try {
                delete(userCommand);
                counterList = addTasks(userCommand);

            } catch (DukeException | StringIndexOutOfBoundsException | NumberFormatException e){
                Ui.printFormattingInvalid(); //Wrong formatting was given

            } catch (IndexOutOfBoundsException e) {
                Ui.printNumberNotInRange(entireList); //Number task has exceeded the range
            }

        } else if(userCommand.equals(Constants.COMMAND_HELP)) {
            Ui.printHelp();

        } else {
            Ui.printHelp();
        }

        //Updating the file
        try {
            writeFile(filePath);

        } catch (IOException e) {
            Ui.printWriteFileError(); //Error when writing into the file
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;

        Ui.printGreetings();

        readFile();

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            run(userCommand);

        }
    }
}
