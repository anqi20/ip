package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static boolean isExit = false;
    public final String filePath = "data/duke.txt";
    public final String directoryPath = "data";

    private final Parser parser;
    private final Storage storage;
    private TaskList tasks;

    public Duke() {
        parser = new Parser();
        storage = new Storage(filePath, directoryPath);
        try {
            tasks = new TaskList(storage.readFile());

        } catch (IOException e) {
            Ui.printReadFileError();
        }
    }

    public void run() {
        Ui.printGreetings();

        while(!isExit) {
            try {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();
                String userCommand = userInput.trim();

                Command c = Parser.processUserCommand(userCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                System.out.println("Something is wrong! GG ");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
