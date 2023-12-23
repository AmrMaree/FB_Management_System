package com.fb.components;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import javafx.scene.ParallelCamera;
import javafx.scene.layout.HBox;

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
            if (users == null) {
                users = new ArrayList<>();
            }
            boolean userExists = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(user.getEmail())) {
                    users.set(i, user);
                    userExists = true;
                    break;
                }
            }
            if (!userExists) {
                users.add(user);
            }
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
            users = gson.fromJson(reader, new TypeToken<List<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static User getUserByUserName(String username) {
        if (users != null) {
            for (User user : users) {
                if (user.getName().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }
    public static User getUserByUserId(int id) {
        if (users != null) {
            for (User user : users) {
                if (user.getId() == id) {
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

    public static boolean checkLogin(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                UserManager.users.remove(user);
                UserManager.users.add(0, user);
                return true;
            }
        }
        return false;
    }

    public static User createAccount(int id, String name, String email, String password, String gender, String birthdate, String rePassword, HBox EValid, HBox PValid, HBox CValid, HBox GenderValidation, HBox NameValidation, HBox  dateValidation) {
        //creating an account
        boolean validName = false;
        boolean validDate = false;
        boolean validGender = false;
        boolean validEmail = false;
        boolean validPassword = false;
        User newUser = new User(id, name, email, password, gender, birthdate);
        while (true) {
            String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getEmail());
            if (!matcher.matches()) {
                EValid.setVisible(true);
                break;
            } else {
                validEmail = true;
                EValid.setVisible(false);
                break;
            }
        }
        //validating password
        while (true) {
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(newUser.getPassword());
            if (matcher.matches()) {
                PValid.setVisible(false);
                if (password.equals(rePassword)) {
                    CValid.setVisible(false);
                    validPassword = true;
                } else {
                    CValid.setVisible(true);
                }
                break;
            } else {
                PValid.setVisible(true);
                break;
            }
        }

            if (gender == null) {
                GenderValidation.setVisible(true);
            } else {
                GenderValidation.setVisible(false);
                validGender = true;
        }

            if (name.isEmpty()) {
                NameValidation.setVisible(true);
            } else {
                NameValidation.setVisible(false);
                validName=true;
            }
        if (birthdate.isEmpty()) {
            dateValidation.setVisible(true);
        } else {
            dateValidation.setVisible(false);
            validDate=true;
        }
        if (validEmail && validPassword && validName && validGender && validDate) {
            users.add(newUser);
            return newUser;
        } else {
            return null;
        }
    }

}




