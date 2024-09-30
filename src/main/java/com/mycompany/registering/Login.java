/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registering;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    
     //Declarations
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    
    //Initialize user details
    public Login(String firstName, String lastName,String username,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    
    //Method to check the username format
    public boolean checkUserName(){
        return username.contains("_")&&username.length()<=5;
    }
    
    //Method to check password complexity
    public boolean checkPasswordComplexity(){
        boolean hasUpperCase = false, hasDigit = false, hasSpecialChar = false;
        if (password.length()>=8){
            for(char c:password.toCharArray()){
                if(Character.isUpperCase(c)) hasUpperCase = true;
                if(Character.isDigit(c)) hasDigit = true;
                if(Character.isLetterOrDigit(c)) hasSpecialChar = true;
            }
            
            return hasUpperCase&&hasDigit&&hasSpecialChar;
        }
        
        return false;
    }
   
    
    //Method to verify User login details
    public boolean loginUser(String enteredUsername, String enteredPassword){
        return enteredUsername.equals(this.username)&&enteredPassword.equals(this.password);
    }
    
    //Method to return login status
    public String returnLoginStatus(boolean isLoggedIn){
        if (isLoggedIn) {
        return"Welcome" + this.firstName + " " + this.lastName + "it is great to see you again.";
    } 
    else {
    return "Username or password incorrect, please try again";
    }
    }
    
    //Method to register the user
    public String registerUser(){
        if(!checkUserName()){
            return "Invalid username. Ensure it contains an underscore and it it not more than 5 characters long.";
        }
        return "Username and password successfully captured.";
        }
    }