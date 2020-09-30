package duke.parser;

import duke.commands.BlahCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.constants.Constants;

/**
 * Processes and understanding the user's commands.
 */
public class Parser {

    /**
     * Processes the input from the user.
     *
     * @param userCommand input from the user.
     * @return command to be executed.
     */
    public static Command processUserCommand(String userCommand) {
        String command = getCommand(userCommand);

        switch(command.toLowerCase()) {
            case Constants.COMMAND_LIST:
                return new ListCommand();

            case Constants.COMMAND_DEADLINE:
                return new DeadlineCommand(userCommand);

            case Constants.COMMAND_EVENT:
                return new EventCommand(userCommand);

            case Constants.COMMAND_TODO:
                return new TodoCommand(userCommand);

            case Constants.COMMAND_BLAH:
                return new BlahCommand();

            case Constants.COMMAND_BYE:
                return new ExitCommand();

            case Constants.COMMAND_DONE:
                return new DoneCommand(userCommand);

            case Constants.COMMAND_DELETE:
                return new DeleteCommand(userCommand);

            case Constants.COMMAND_HELP:
                return new HelpCommand();

            default:
                return new HelpCommand();
        }

    }

    /**
     * Get the command word of the user.
     *
     * @param userCommand input from the user.
     * @return String with command, without the task description.
     */
    private static String getCommand(String userCommand) {
        String[] command = userCommand.split(" ");

        return command[0];
    }
}
