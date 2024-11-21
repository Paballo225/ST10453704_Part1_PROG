/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registering;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


/**
 *
 * @author RC_Student_lab
 */

/**
 * Main class for task registration and management.
 */
public class Registering {

    public static void main(String[] args) {
        Registering.register();
    }

    private static List<String> developers = new ArrayList<>();
    private static List<String> taskNames = new ArrayList<>();
    private static List<Integer> taskIDs = new ArrayList<>();
    private static List<Integer> taskDurations = new ArrayList<>();
    private static List<String> taskStatuses = new ArrayList<>();
    private static int taskIdCounter = 1;
    private static List<Task> tasks = new ArrayList<>();

    public static void register() {
        Scanner sc = new Scanner(System.in);

        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        // Registration
        System.out.println("Enter your first name:");
        String firstName = sc.next();

        System.out.println("Enter your last name:");
        String lastName = sc.next();

        String username;
        do {
            System.out.println("Enter your username (must contain an underscore and be no more than 5 characters):");
            username = sc.next();
        } while (!isValidUsername(username));

        String password;
        do {
            System.out.println("Enter your password (must be at least 8 characters, contain a capital letter, a number, and a special character):");
            password = sc.next();
        } while (!isValidPassword(password));

        // Create user object
        Login user = new Login(firstName, lastName, username, password);
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equalsIgnoreCase("Username and password successfully captured.")) {
            // Login process
            System.out.println("Enter your username to login:");
            String enteredUsername = sc.next();
            System.out.println("Enter your password:");
            String enteredPassword = sc.next();

            boolean isLoggedIn = user.loginUser(enteredUsername, enteredPassword);
            System.out.println(user.returnLoginStatus(isLoggedIn));
        }

        // Menu for task management
        int option = 0;
        while (option != 3) {
            String menu = "1. Add task\n2. Show report\n3. Quit\n4. Display done tasks\n5. Search task by name\n6. Search tasks by developer\n7. Delete task";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    addTasks();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, generateReport());
                    break;
                case 3:
                    System.exit(0);
                    break;
                case 4:
                    displayTasksWithStatus("Done");
                    break;
                case 5:
                    searchTaskByName(JOptionPane.showInputDialog("Enter task name to search:"));
                    break;
                case 6:
                    searchTasksByDeveloper(JOptionPane.showInputDialog("Enter developer to search tasks:"));
                    break;
                case 7:
                    deleteTask(JOptionPane.showInputDialog("Enter task name to delete:"));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    // Valid username
    private static boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Valid password
    private static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.chars().anyMatch(Character::isUpperCase) &&
               password.chars().anyMatch(Character::isDigit) &&
               password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:',.<>?/`~".indexOf(ch) >= 0);
    }

    // Adding tasks
    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks:"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription;
            do {
                taskDescription = JOptionPane.showInputDialog("Enter task description (less than 50 characters):");
                if (taskDescription.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                }
            } while (taskDescription.length() > 50);

            String developerDetails = JOptionPane.showInputDialog("Enter developer details:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (hours):"));
            String taskStatus = JOptionPane.showInputDialog("Select status \n1. To Do \n2. Done \n3. Doing");

            Task task = new Task(taskName, taskIdCounter++, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks.add(task);
            developers.add(developerDetails);
            taskNames.add(taskName);
            taskIDs.add(task.getTaskNumber());
            taskDurations.add(taskDuration);
            taskStatuses.add(taskStatus);
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
    }

    private static String generateReport() {
        StringBuilder report = new StringBuilder("Task Report:\n\n");
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n");
        }
        return report.toString();
    }

    // Additional functionalities
    public static void displayTasksWithStatus(String status) {
        for (Task task : tasks) {
            if (task.getTaskStatus().equalsIgnoreCase(status)) {
                System.out.println("Developer: " + task.getDeveloperDetails() + ", Task: " + task.getTaskName() + ", Duration: " + task.getTaskDuration());
            }
        }
    }

    public static void displayLongestTask() {
        Task longestTask = Collections.max(tasks, Comparator.comparingInt(Task::getTaskDuration));
        System.out.println("Developer: " + longestTask.getDeveloperDetails() + ", Duration: " + longestTask.getTaskDuration());
    }

    public static void searchTaskByName(String name) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(name)) {
                System.out.println("Task: " + task.getTaskName() + ", Developer: " + task.getDeveloperDetails() + ", Status: " + task.getTaskStatus());
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public static void searchTasksByDeveloper(String developer) {
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(developer)) {
                System.out.println("Task: " + task.getTaskName() + ", Status: " + task.getTaskStatus());
            }
        }
    }

    public static void deleteTask(String name) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Entry \"" + name + "\" successfully deleted");
                return;
            }
        }
        System.out.println("Task not found.");
    }
}
