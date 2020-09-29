package duke.parser;

import duke.commands.*;
import duke.constants.Constants;

public class Parser {

    public Command processUserCommand(String userCommand) {

        switch(userCommand.toLowerCase()) {
            case Constants.COMMAND_LIST:

            case Constants.COMMAND_DEADLINE:

            case Constants.COMMAND_EVENT:

            case Constants.COMMAND_TODO:

            case Constants.COMMAND_BLAH:
                return new BlahCommand();

            case Constants.COMMAND_BYE:
                return new ExitCommand();

            case Constants.COMMAND_DONE:

            case Constants.COMMAND_DELETE:
                return new DeleteCommand(userCommand);

            case Constants.COMMAND_HELP:
                return new HelpCommand();

            default:
                return new HelpCommand();
        }
    }

}
