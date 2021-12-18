package com.abhauction.utilities;

import java.util.Random;

public class StringResources {

    public static String loggedIn = "ERROR: User is still logged In after log out!";

    public static String invalidLogIn = "ERROR: Unknown User logged in!";

    public static String error404(String error){ return error + " page element not found!"; }

    public static String formInput(String error){ return "Form validation error: " + error;}

    public static String invalidElement(String error){ return  "Invalid element on display: " + error;}

    public static String sortingError(String error){ return  "Incorrect sorting: " + error;}

    public static String randomEmail(){
        Random rand = new Random();
        int length = rand.nextInt(11-5)+5;
        String CHARSEQ = "abcdefghijklmnopqrstuvwxyz-._1234567890";
        StringBuilder name = new StringBuilder();

        for(int i=0; i<length; i++){
            Character r = CHARSEQ.charAt(rand.nextInt(39));
            if(i==0 || i==length-1){
                while(r == '-' || r == '.' || r == '_'){
                    r = CHARSEQ.charAt(rand.nextInt(39));
                }
            }
            name.append(r);
        }
        return name.toString();
    }

    public static String randomUserEmail(){
        return randomEmail()+"@mail.com";
    }

    //User info
    public static String randomMailStatic = randomEmail()+"@mail.com";
    public static String validName = "alenn";
    public static String invalidName = "w1#';qq";
    public static String existingEmail = "alenn@mail.com";
    public static String standardPass = "12345";
    public static String unknownEmail = "123asad3342ffff@mail.com";
    public static String invalidEmail = randomEmail()+"#_@mail.com";
    public static String badPass = "1111";
}
