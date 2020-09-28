package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public static void printDashLine(){
        System.out.println("--------------------------------------------------");
    }

    public static void printGreetings() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        printDashLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printDashLine();
    }

    public static void printBlah() {
        printDashLine();
        System.out.println("Blah blah blah blah blah");
        System.out.println("Hahaha, kidding! What were you saying again?");
        printDashLine();
    }

    public static void printBye() {
        printDashLine();
        System.out.println("Bye bye. Hope you have a nice day and see you soon!");
        printDashLine();
    }

    public static void printEmptyList() {
        printDashLine();
        System.out.println("There is nothing on your list! Add something to begin! ");
        printDashLine();
    }

    public static void printEntireList(ArrayList<Task> list) {
        int i = 1;
        for(Task item: list) {
            if(item != null) {
                System.out.println(i + ". " + item.toString());
                i++;
            }
        }
    }

    public static void printCurrentList(ArrayList<Task> list) {
        printDashLine();
        System.out.println("Here is your list!");
        printEntireList(list);
        printDashLine();
    }

    public static void printDone(ArrayList<Task> list, int taskNum) {
        printDashLine();
        System.out.println("Congratulations! You have completed: ");
        System.out.println((taskNum + 1) + ". " + list.get(taskNum).toString());
        printDashLine();
    }

    public static void printDoneWhenDone(ArrayList<Task> list) {
        printDashLine();
        System.out.println("You have already completed this task!");
        System.out.println("This is the current list that you have: ");
        printEntireList(list);
        printDashLine();
    }

    public static void printDeleteTask(String s, int counterList) {
        printDashLine();
        System.out.println("Okie! I've removed this task: ");
        System.out.println(s);
        System.out.print("Now you have " + counterList + " task");
        if(counterList <= 1) {
            System.out.print(" ");
        } else {
            System.out.print("s ");
        }
        System.out.println("in the list.");
        printDashLine();
    }

    public static void printAddTask(String s, int counterList) {
        printDashLine();
        System.out.println("Got it! I've added this task: ");
        System.out.println(s);
        System.out.print("Now you have " + counterList + " task");
        if (counterList == 1) {
            System.out.print(" ");
        } else {
            System.out.print("s ");
        }
        System.out.println("in the list.");
        printDashLine();
    }

    public static void printHelp() {
        printDashLine();
        System.out.println("You must be confused! \u2719_\u2719");
        System.out.println("Here is the list of commands that I can understand: ");
        System.out.println("1) list");
        System.out.println("2) blah");
        System.out.println("3) todo ...");
        System.out.println("4) event ... /at ...");
        System.out.println("5) deadline ... /by ...");
        System.out.println("6) done ...");
        System.out.println("7) delete ... ");
        System.out.println("8) help");
        System.out.println("9) bye");
        printDashLine();
    }

    public static void printFormattingInvalid() {
        printDashLine();
        System.out.println("There's something wrong with your formatting!");
        System.out.println("If you are unsure, use \"help\" to check the formatting. ");
        printDashLine();
    }

    public static void printNumberNotInRange(ArrayList<Task> list) {
        printDashLine();
        System.out.println("Errr... I don't think you have this item on the list.");
        System.out.println("This is the current list that you have:");
        printEntireList(list);
        printDashLine();
    }

    public static void printReadFileError() {
        printDashLine();
        System.out.println("Oh man! An error has occurred when we try to read the file. ");
        System.out.println("Please check again! ");
        printDashLine();
    }

    public static void printReadFileInvalid() {
        printDashLine();
        System.out.println("Oops! Invalid task was detected!");
        printDashLine();
    }

    public static void printWriteFileError() {
        printDashLine();
        System.out.println("Oh dear! An error has occurred when we try to write into the file. ");
        System.out.println("Please check again! ");
        printDashLine();
    }
}