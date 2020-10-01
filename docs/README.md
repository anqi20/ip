# User Guide 

This is a task manager that handles your todos, events and deadlines. 

## Setting up the program 

Prerequisites: Java 11.

1. Download the jar file. 
2. Open the command prompt. 
3. Go into the directory containing the ip.jar file. 
    1. Use `cd` to enter the directory. 
    2. Use `ls` or `dir` to list all the files in the specific directory. 
4. Type `java -jar ip.jar` into the command prompt. 
5. Enter to start the program. 


   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
   --------------------------------------------------
   Hello! I'm Duke 
   What can I do for you? 
   --------------------------------------------------
   ```
 ##Features 
 * [Adding a deadline task](#deadline)
 * [Adding an event task](#event)
 * [Adding a todo task](#todo)
 * [Deleting a task](#delete)
 * [Marking a task as done](#done)
 * [Finding keyword in tasks](#find)
 * [Listing all tasks](#list)
 * [Help](#help)
 * [Blah](#blah)
 * [Exiting program](#exit)
 
 <a name="deadline"></a>
 ###`deadline` - Adding a deadline task 
 Adds a deadline task to the list of tasks. 
 
 * Format: `deadline <task description> /by <date>`
 
 * Example: `deadline return book /by Tuesday`
 
 * Expected outcome: 
 ```
--------------------------------------------------
Got it! I've added this task: 
[D][✘] return book (by: Tuesday)
Now you have 1 task in the list.
--------------------------------------------------
```
 
 <a name="event"></a>
 ###`event` - Adding an event task 
 Adds an event task to the list of tasks. 
  
  * Format: `event <task description> /at <date>`
  
  * Example: `event project meeting /at Tuesday`
  
  * Expected outcome:  
  ```
--------------------------------------------------
Got it! I've added this task: 
[E][✘] project meeting (at: Tuesday)
Now you have 2 tasks in the list.
--------------------------------------------------
 ```
 
 <a name="todo"></a>
 ###`todo` - Adding a todo task 
 Adds a deadline task to the list of tasks. 
  
  * Format: `todo <task description>`
  
  * Example: `todo read book`
  
  * Expected outcome: 
  ```
--------------------------------------------------
Got it! I've added this task: 
[T][✘] read book
Now you have 3 tasks in the list.
--------------------------------------------------
 ```
 
 <a name="delete"></a>
 ###`delete` - Deleting a task 
 Deletes a task from the list of tasks. 
   
   * Format: `delete <task index>`
   
   * Example: `delete 1`
   
   * Expected outcome: 
   ```
--------------------------------------------------
Okie! I've removed this task: 
[D][✘] return book (by: Tuesday)
Now you have 2 tasks in the list.
--------------------------------------------------
  ```
 
 <a name="done"></a> 
 ###`done` - Marking a task as done 
 Marks a task as completed. 
   
   * Format: `done <task index>`
   
   * Example: `done 2`
   
   * Expected outcome: 
   ```
--------------------------------------------------
Congratulations! You have completed: 
2. [E][✓] project meeting (at: Tuesday)
--------------------------------------------------
  ```
 
 <a name="find"></a> 
 ###`find` - Finding keyword in tasks 
 Finds a keyword in the list of tasks. 
   
   * Format: `find <keyword>`
   
   * Example: `find book`
   
   * Expected outcome: 
   ```
--------------------------------------------------
Here are the matching tasks in your list: 
1. [D][✘] return book (by: Tuesday)
2. [T][✘] read book
--------------------------------------------------
  ```
 
 <a name="list"></a> 
 ###`list` - Listing all tasks 
 Lists all the tasks. 
   
   * Format: `list`
   
   * Expected outcome: 
   ```
--------------------------------------------------
Here is your list!
1. [E][✓] project meeting (at: Tuesday)
2. [T][✘] read book
--------------------------------------------------
  ```
 
 <a name="help"></a>
 ###`help` - Help 
 Lists out all the commands that can be used. 
   
   * Format: `help`
   
   * Expected outcome: 
   ```
--------------------------------------------------
You must be confused! ✙_✙
Here is the list of commands that I can understand: 
1) list
2) blah
3) todo ...
4) event ... /at ...
5) deadline ... /by ...
6) done ...
7) delete ... 
8) find ...
9) help
10) bye
--------------------------------------------------
  ```
 
 <a name="blah"></a> 
 ###`blah` - Additional fun 
 Try this command to find out what would Duke say! 
   
   * Format: `blah`
 
 <a name="exit"></a> 
 ###`bye` - Exiting program 
 Exits out from the entire program. 
   
   * Format: `bye`
   
   * Expected outcome: 
   ```
--------------------------------------------------
Bye bye. Hope you have a nice day and see you soon!
--------------------------------------------------
  ```
##Saving of data 
Duke will automatically save the data into a local storage in the form of a text file. 
The `ip.jar` file will be saved together with another data folder. 
This data folder will contain the `./data/duke.txt` file. 
 