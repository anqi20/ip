import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userCommand;
        String[] entireList = new String[100];
        int counterList = 0;
        boolean isExit = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Level 0: Greet
        System.out.println("--------------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("--------------------------------------------------");

        //Level 1: Greet, Echo, Exit
        while(!isExit) {
            userCommand = in.nextLine();

            if (userCommand.equals("list")) {
                System.out.println("--------------------------------------------------");
                System.out.println("Here is your list!");
                for(int i=0; i<counterList; i++) {
                    System.out.println((i+1) + ". " + entireList[i]);
                }
                System.out.println("--------------------------------------------------");
            } else if (userCommand.equals("blah")) {
                System.out.println("--------------------------------------------------");
                System.out.println("Blah blah blah blah blah");
                System.out.println("Hahaha, kidding! What were you saying again?");
                System.out.println("--------------------------------------------------");
            } else if (userCommand.equals("bye")) {
                System.out.println("--------------------------------------------------");
                System.out.println("Bye. Hope you have a nice day and see you soon!");
                System.out.println("--------------------------------------------------");
                isExit = true;
            } else {
                entireList[counterList] = userCommand;
                counterList++;
                System.out.println("--------------------------------------------------");
                System.out.println("added: " + userCommand);
                System.out.println("--------------------------------------------------");
            }

            /*else {
                System.out.println("--------------------------------------------------");
                System.out.println("Errr... I don't really understand what you are saying.");
                System.out.println("Here is the list of commands that I can understand: ");
                System.out.println("1) list\n2) blah\n3) bye");
                System.out.println("--------------------------------------------------");
            }*/
        }
    }
}
