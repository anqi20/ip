package duke.storage;

import duke.constants.Constants;
import duke.task.Deadline;
import duke.task.Events;
import duke.parser.Parser;
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
    public static String filePath;
    public static String directoryPath;

    public Storage(String filePath, String directoryPath) {
        Storage.filePath = filePath;
        Storage.directoryPath = directoryPath;
    }

    public static ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> entireList = new ArrayList<>();
        int numberOfTasks = 0;

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
                String userCommandBy =
                        line.substring(dividerPosition + Constants.LENGTH_OF_AT +1, line.length()-1).trim();
                Events e = new Events(userCommandName, userCommandBy);
                entireList.add(numberOfTasks, e);

            } else if(line.startsWith(Constants.PRINT_DEADLINE)) {

                int dividerPosition = line.indexOf(Constants.PRINT_DEADLINE_BY);
                String userCommandName = line.substring(Constants.LENGTH_OF_INPUT_FORMAT +1, dividerPosition).trim();
                String userCommandBy =
                        line.substring(dividerPosition + Constants.LENGTH_OF_BY +1, line.length()-1).trim();
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
        return entireList;
    }

    public static void writeFile(ArrayList<Task> entireList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for(int i=0; i<entireList.size(); i++){
                if(entireList.get(i) != null) {
                    fw.write(entireList.get(i).toString() + System.lineSeparator());
                }
            }
            fw.close();

        } catch(IOException e) {
            Ui.printWriteFileError();
        }

    }
}
