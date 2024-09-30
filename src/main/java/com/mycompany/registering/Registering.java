/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registering;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Registering {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Registration
        System.out.println("Enter your first name:");
        String firstName = sc.next();
        
        System.out.println("Enter your last name:");
        String lastName = sc.next();
        
        System.out.println("Enter your username(must contain an underscore and be no more than 5 characters):");
        String username = sc.next();
        
        System.out.println("Enter your password(must be atleast 8 characters, contain a capital letter, a number and a special character):");
        String password = sc.next();
        
        //Object
        Login user = new Login(firstName, lastName, username, password);
        
        
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);
        
        if(registrationMessage.equals("username and password successfully captured")){
            
            //Login user
            System.out.println("Enter your username to login");
            String enteredUsername = sc.next();
            
            System.out.println("Enter your password");
            String enteredPassword = sc.next();
            
            boolean isLoggedIn = user.loginUser(enteredUsername, password);
            System.out.println(user.returnLoginStatus(isLoggedIn));
            
        }
    }
}

      
       
