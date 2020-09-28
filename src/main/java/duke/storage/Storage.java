package duke.storage;

import duke.constants.Constants;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String filePath = "data/duke.txt";
    public static final String directoryPath = "data";

    public static int readFile(ArrayList<Task> entireList, int numberOfTasks) {
        try {
            numberOfTasks = executeReadFile(entireList, numberOfTasks);

        } catch (IOException e) {
            Ui.printReadFileError(); //Input file has an error
        }
        return numberOfTasks;
    }

    public static void writeFile(ArrayList<Task> entireList) {
        try {
            executeWriteFile(filePath, entireList);

        } catch (IOException e) {
            Ui.printWriteFileError();
        }
    }

    public static int executeReadFile(ArrayList<Task> entireList,
                                    int numberOfTasks) throws IOException {
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
                entireList.add(numberOfTasks, t);

            } else if (line.startsWith(Constants.PRINT_EVENT)) {

                int dividerPosition = line.indexOf(Constants.PRINT_EVENT_AT);
                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + Constants.LENGTH_OF_BY +1, line.length()-1).trim();
                Events e = new Events(userCommandName, userCommandBy);
                entireList.add(numberOfTasks, e);

            } else if(line.startsWith(Constants.PRINT_DEADLINE)) {

                int dividerPosition = line.indexOf(Constants.PRINT_DEADLINE_BY);
                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy = line.substring(dividerPosition + Constants.LENGTH_OF_BY +1, line.length()-1).trim();
                Deadline d = new Deadline(userCommandName, userCommandBy);
                entireList.add(numberOfTasks, d);

            } else {
                //Invalid task found
                Ui.printReadFileInvalid();
            }
            if (taskDone.equals(Constants.TICK_HTML_CODE)) {
                entireList.get(numberOfTasks).markAsDone();
            }
            numberOfTasks++;
        }
        return numberOfTasks;
    }

    public static void executeWriteFile(String filePath, ArrayList<Task> entireList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task item : entireList){
            if(item != null) {
                fw.write(item.toString() + System.lineSeparator());
            }
        }
        fw.close();
    }

}
