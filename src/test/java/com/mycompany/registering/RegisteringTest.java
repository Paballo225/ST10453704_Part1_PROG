/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registering;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class RegisteringTest {
    
    public RegisteringTest() {
    }

    private Task[] tasks;

    @BeforeEach
    public void setUp() {
        tasks = new Task[4];
        tasks[0] = new Task("Create Login", 1, "Login feature", "Mike Smith", 5, "To Do");
        tasks[1] = new Task("Create Add Features", 2, "Add features to the app", "Edward Harrison", 8, "Doing");
        tasks[2] = new Task("Create Reports", 3, "Generate reports", "Samantha Paulson", 2, "Done");
        tasks[3] = new Task("Add Array", 4, "Implement array handling", "Glenda Oberholzer", 11, "To Do");
    }

    @Test
    public void testDevelopersArray() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        for (int i = 0; i < expectedDevelopers.length; i++) {
            assertEquals(expectedDevelopers[i], tasks[i].getDeveloperDetails());
        }
    }

    @Test
    public void testLongestTaskDuration() {
        int maxDuration = 0;
        String longestTaskDeveloper = "";
        String longestTaskName = "";

        for (Task task : tasks) {
            if (task.getTaskDuration() > maxDuration) {
                maxDuration = task.getTaskDuration();
                longestTaskDeveloper = task.getDeveloperDetails();
                longestTaskName = task.getTaskName();
            }
        }

        assertEquals("Glenda Oberholzer", longestTaskDeveloper);
        assertEquals(11, maxDuration);
    }

    @Test
    public void testSearchTaskByName() {
        String searchTaskName = "Create Login";
        Task foundTask = null;

        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(searchTaskName)) {
                foundTask = task;
                break;
            }
        }

        assertNotNull(foundTask);
        assertEquals("Mike Smith", foundTask.getDeveloperDetails());
        assertEquals("Create Login", foundTask.getTaskName());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String searchDeveloper = "Samantha Paulson";
        StringBuilder developerTasks = new StringBuilder("Tasks for Developer: " + searchDeveloper + "\n");

        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(searchDeveloper)) {
                developerTasks.append("Task Name: ").append(task.getTaskName()).append("\n");
            }
        }

        assertEquals("Tasks for Developer: Samantha Paulson\nTask Name: Create Reports\n", developerTasks.toString());
    }

    @Test
    public void testDeleteTask() {
        String deleteTaskName = "Create Reports";
        boolean taskDeleted = false;
        int deletedIndex = -1;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].getTaskName().equalsIgnoreCase(deleteTaskName)) {
                deletedIndex = i;
                taskDeleted = true;
                break;
            }
        }

        // Shift tasks down in the array only if task was found
        if (taskDeleted) {
            for (int j = deletedIndex; j < tasks.length - 1; j++) {
                tasks[j] = tasks[j + 1];
            }
            tasks[tasks.length - 1] = null; // Remove last element
        }

        assertTrue(taskDeleted);
        assertNull(tasks[tasks.length - 1]); // The last task should now be null after deletion
    }

    @Test
    public void testDisplayReport() {
        StringBuilder taskSummary = new StringBuilder("Task Summary: \n\n");
        for (Task task : tasks) {
            if (task != null) {
                taskSummary.append(task.printTaskDetails()).append("\n\n");
            }
        }

        assertNotNull(taskSummary.toString());
        assertEquals(3, taskSummary.toString().split("\n\n").length - 1); // Check that there are 3 tasks remaining
    }
}
