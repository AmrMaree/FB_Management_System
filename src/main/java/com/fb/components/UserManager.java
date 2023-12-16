package com.fb.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.lang.reflect.Type;
import java.util.regex.*;
import java.text.SimpleDateFormat;

public class UserManager {
    public static List<User> users = deserialize("UserInfo.json");
    public static void serialize(User user, String filename) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Ensure that the users list is not null
            if (users == null) {
                users = new ArrayList<>();
            }
            // Check if the user already exists in the list
            boolean userExists = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(user.getEmail())) {
                    // If the user already exists, update the existing user
                    users.set(i, user);
                    userExists = true;
                    break;
                }
            }
            // If the user doesn't exist, add the new user to the list
            if (!userExists) {
                users.add(user);
            }
            // Write the combined data back to the file
            try (FileWriter writer = new FileWriter(filename)) {
                gson.toJson(users, writer);
            }
            System.out.println("Serialized data is updated in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<User> deserialize(String filename) {
        List<User> users = new ArrayList<>();

        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
    public static User getUserByEmail(String email, String filename) {
        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        return null;
    }
    public static int getGreatestUserId() {
        int greatestId = -1;
        for (User user : users) {
            int userId = user.getId();
            if (userId > greatestId) {
                greatestId = userId;
            }
        }
        return greatestId;
    }
    public static boolean checkLogin(String email, String password, String filename) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                UserManager.users.remove(user);
                UserManager.users.add(0, user);
                return true;
            }
        }
        return false;
    }
    public User createAccount(int id ,String name, String email, String password, String gender, String birthdate, String rePassword){
        //creating an account
        boolean validEmail = false;
        boolean validPassword = false;
        User newUser = new User(id,name, email, password, gender, birthdate);
        while(true){
            String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getEmail());
            if(!matcher.matches()){
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
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getPassword());
            if(matcher.matches()){
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
        if(validEmail && validPassword){
            users.add(newUser);
            return newUser;
        }
        else{
            return null;
        }
    }
}


