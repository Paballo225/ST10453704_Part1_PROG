/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registering;

/**
 *
 * @author RC_Student_lab
 */
public class Task {
    
    
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private String taskID;
    private int taskDuration;
    private String taskStatus;
    
    public Task(String taskName, int taskNumber, String taskDecsription, String developerDetails, int taskDuration, String taskStatus1){
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskStatus = "To Do";
        this.taskID = createTaskID();
        this.taskDuration =taskDuration;
    }
    
    public boolean checkTaskDescription(String description){
        return description.length() >50;
    }
    
    public String createTaskID(){
        String taskID = (taskName.substring(0, 2) + ":" + taskNumber + ":" 
                + developerDetails.substring(developerDetails.length()- 3)).toUpperCase();
        return taskID;
    }
    
    
    public String printTaskDetails(){
        return "Task Status:" + taskStatus 
                + "\nDeveloper Details:" + developerDetails 
                + "\nTask Number:" + taskNumber 
                + "\nTask Name:" + taskName
                + "\nTask Description:" + taskDescription 
                + "\nTask ID:" + taskID 
                + "\nDuration:" + taskDuration 
                + "hours";
    }
    
    public int returnTotalHours(){
        return taskDuration;
    }
}
