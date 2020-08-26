import java.util.Scanner;

public class Duke {

    public static void printEntireList(Task[] list) {
        int i = 0;
        for(Task item: list) {
            if(item != null) {
                System.out.println((i+1) + ". [" + item.getStatusIcon() + "] " + item.getDescription());
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        Task[] entireList = new Task[100];
        int counterList = 0;
        boolean isExit = false;

        String dashLine = "--------------------------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greetings
        System.out.println(dashLine);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(dashLine);

        //Different Commands: list, ?, blah, bye, done... ,(others)
        while(!isExit) {
            userInput = in.nextLine();
            String userCommand = userInput.trim();

            if (userCommand.equals("list")) {
                System.out.println(dashLine);
                System.out.println("Here is your list!");
                printEntireList(entireList);
                System.out.println(dashLine);
            }

            else if(userCommand.equals("?")) {
                System.out.println(dashLine);
                System.out.println("Hi, you must be confused! \u2719_\u2719");
                System.out.println("Here is the list of commands that I can understand: ");
                System.out.println("1) list\n2) blah\n3) bye\n4) Type in anything that you want to be tracked");
                System.out.println(dashLine);
            }

            else if (userCommand.equals("blah")) {
                System.out.println(dashLine);
                System.out.println("Blah blah blah blah blah");
                System.out.println("Hahaha, kidding! What were you saying again?");
                System.out.println(dashLine);
            }

            else if (userCommand.equals("bye")) {
                System.out.println(dashLine);
                System.out.println("Bye bye. Hope you have a nice day and see you soon!");
                System.out.println(dashLine);
                isExit = true;
            }

            else if(userCommand.startsWith("done")) {
                String taskNumString = userCommand.substring(4);
                int taskNumInt = Integer.parseInt(taskNumString.trim()) - 1;

                if((taskNumInt < counterList) && (taskNumInt >= 0)) {
                    entireList[taskNumInt].markAsDone();
                    System.out.println(dashLine);
                    System.out.println("Congratulations! You have completed: ");
                    System.out.println("[\u2713] " + entireList[taskNumInt].getDescription());
                    System.out.println(dashLine);
                }
                else {
                    System.out.println(dashLine);
                    System.out.println("Errr... I don't think you have so much items on the list.");
                    System.out.println("This is the current list that you have:");
                    printEntireList(entireList);
                    System.out.println(dashLine);
                }
            }

            else {
                entireList[counterList] = new Task(userCommand);
                counterList++;

                System.out.println(dashLine);
                System.out.println("Newly added: " + userCommand);
                System.out.println(dashLine);
            }
        }
    }
}
