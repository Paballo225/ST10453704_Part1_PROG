/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registering;
import java.util.Scanner;
import javax.swing.*;


/**
 *
 * @author RC_Student_lab
 */
public class Registering {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        
        //Registration
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
            String menu = "1. Add task\n2. Show report\n3. Quit";
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (option) {
                case 1:
                    addTasks();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    private static boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8 && 
               password.chars().anyMatch(Character::isUpperCase) &&
               password.chars().anyMatch(Character::isDigit) &&
               password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:',.<>?/`~".indexOf(ch) >= 0);
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks:"));
        Task[] tasks = new Task[numTasks];

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

            tasks[i] = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            JOptionPane.showMessageDialog(null, tasks[i].printTaskDetails());
        }

        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.returnTotalHours();
        }
        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
    }
}

