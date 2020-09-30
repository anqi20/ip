package duke.parser;

import duke.commands.*;
import duke.constants.Constants;
import duke.exception.DukeException;

public class Parser {

    public static Command processUserCommand(String userCommand) throws DukeException {
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

    public static String getCommand(String userCommand) {
        String[] command = userCommand.split(" ");

        return command[0];
    }
}
