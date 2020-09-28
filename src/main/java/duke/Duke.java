package duke;

import duke.constants.Constants;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> entireList = new ArrayList<>();
    public static int numberOfTasks = 0;
    public static boolean isExit = false;

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
        numberOfTasks--;
        Ui.printDeleteTask(t.toString(), numberOfTasks);
    }

    public static int addTasks(String userCommand) throws DukeException {

        if(userCommand.toLowerCase().startsWith(Constants.COMMAND_DEADLINE)) {

            if(!userCommand.contains(Constants.COMMAND_DEADLINE_BY)) {
                throw new DukeException();
            }
            Deadline d = getDeadline(userCommand);
            entireList.add(numberOfTasks, d);
            numberOfTasks++;
            Ui.printAddTask(d.toString(), numberOfTasks);

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_EVENT)) {

            if(!userCommand.contains(Constants.COMMAND_EVENT_AT)) {
                throw new DukeException();
            }
            Events e = getEvents(userCommand);
            entireList.add(numberOfTasks, e);
            numberOfTasks++;
            Ui.printAddTask(e.toString(), numberOfTasks);

        } else if (userCommand.toLowerCase().startsWith(Constants.COMMAND_TODO)) {

            ToDos t = getToDos(userCommand);
            entireList.add(numberOfTasks, t);
            numberOfTasks++;
            Ui.printAddTask(t.toString(), numberOfTasks);

        }
        return numberOfTasks;
    }

    public static void run(String userCommand) {
        if (userCommand.toLowerCase().equals(Constants.COMMAND_LIST)) {

            if(numberOfTasks == 0) {
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
                numberOfTasks = addTasks(userCommand);

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
                numberOfTasks = addTasks(userCommand);

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

        Storage.writeFile(entireList);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;

        Ui.printGreetings();

        numberOfTasks = Storage.readFile(entireList, numberOfTasks);

        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            run(userCommand);

        }
    }
}
