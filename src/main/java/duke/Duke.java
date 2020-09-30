package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke Class.
 * Greet user and process the user's commands.
 */
public class Duke {
    public static boolean isExit = false;
    public final String filePath = "data/duke.txt";
    public final String directoryPath = "data";

    private final Parser parser;
    private final Storage storage;
    private TaskList tasks;

    private Duke() {
        parser = new Parser();
        storage = new Storage(filePath, directoryPath);
        try {
            tasks = new TaskList(storage.readFile());

        } catch (IOException e) {
            Ui.printReadFileError();
        }
    }

    private void run() {
        Ui.printGreetings();

        while(!isExit) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            String userCommand = userInput.trim();

            Command c = Parser.processUserCommand(userCommand);
            c.execute(tasks, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
