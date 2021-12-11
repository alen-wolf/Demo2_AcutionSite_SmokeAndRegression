package com.abhauction.utilities;

import java.util.Random;

public class StringResources {

    public static String error404(String error){ return error + " page element not found!"; }

    public static String formInput(String error){ return "Form validation error: " + error;}

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
}
