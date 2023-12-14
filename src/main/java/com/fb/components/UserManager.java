package com.fb.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.reflect.Type;
import java.util.regex.*;
import java.text.SimpleDateFormat;



public class UserManager {
    public static void serialize(User user, String filename) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Read the existing JSON data from the file
            List<User> users = deserialize(filename);

            // Add the new user to the existing data
            users.add(user);

            // Write the combined data back to the file
            FileWriter writer = new FileWriter(filename);
            gson.toJson(users, writer);
            writer.close();

            System.out.println("Serialized data is appended to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> deserialize(String filename) {
        List<User> users = new ArrayList<>();
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            users = gson.fromJson(reader, new ArrayList<User>().getClass());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean checkLogin(String email, String password, String filename) {
        try {
            Gson gson = new Gson();

            // Use TypeToken for generic type information
            Type userListType = new TypeToken<List<User>>() {}.getType();

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                // Use TypeToken to handle generic types
                List<User> users = gson.fromJson(reader, userListType);

                if (users != null) {
                    // Use enhanced for loop for better readability
                    for (User user : users) {
                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log, throw, etc.)
        }
        return false;
    }
    public User createAccount(String name, String email, String password, String gender, String birthdate, String rePassword){
        //creating an account
        boolean validEmail = false;
        boolean validPassword = false;
        boolean validDate = false;
        User newUser = new User(name, email, password, gender, birthdate);
        while(true){
            String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getEmail());
            if(matcher.matches() == false){

                System.out.println("the email is invalid please enter a valid email");
                break;
            }
            else{
                validEmail = true;
                break;
            }
        }
        //validating password
        while(true){
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
            //System.out.println("Must have at least one numeric character\n" +
            // "Must have at least one lowercase character\n" +
            // "Must have at least one uppercase character\n" +
            //  "Must have at least one special symbol among @#$%\n" +
            //  "Password length should be between 8 and 20");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getPassword());
            if(matcher.matches() == true){
                if(password != rePassword){
                    validPassword = true;
                }
                else{
                    System.out.println("Two password fields must be the same");
                }
                break;
            }
            else{
                break;
            }
        }
        //validating gender
        /*while(true){
            String regex= "(?:m|M|male|Male|f|F|female|Female|FEMALE|MALE|Not prefer to say)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.gender);
            if(matcher.matches()==true){
                break;
            }else{
                System.out.println("enter a valid gender Male, female or Not prefer to say ");
                continue;
            }
        }*/
        //validating birthdate
        while(true){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                Date d1 = sdf.parse(newUser.getBirthDate());
                validDate = true;
                break;
            } catch (Exception e) {
                System.out.println("enter a valid birthdate");
                break;
            }
        }
        if(validEmail && validPassword && validDate){
            return newUser;
        }
        else{
            return null;
        }
    }
}

