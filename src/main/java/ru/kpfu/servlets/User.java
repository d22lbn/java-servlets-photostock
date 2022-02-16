package ru.kpfu.servlets;

import java.util.ArrayList;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private int age;
    private int balance;
    private String ava;

    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(ArrayList<String> data) {
        this.email = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.age = 0;        
        this.balance = 0;
        this.ava = "";
    }

//    public User(ArrayList<String> data) {
//        int i = 0;
//        this.id = Integer.parseInt(data.get(i++));
//        this.name = data.get(i++);
//        this.surname = data.get(i++);
//        
//        this.age = Integer.parseInt(data.get(i++));
//        this.email = data.get(i++);
//        this.password = data.get(i++);
//        this.balance = Integer.parseInt(data.get(i++));
//        this.ava = data.get(i++);
//    }

    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(getCoup("email", email));
        data.add(getCoup("password", password));
        data.add(getCoup("name", name));
        data.add(getCoup("surname", surname));
        data.add(getCoup("age", Integer.toString(age)));
        data.add(getCoup("balance", Integer.toString(balance)));
        data.add(getCoup("ava", ava));
        return data;
    }

    public static ArrayList<ArrayList<String>> getParameters() {
        ArrayList<ArrayList<String>> parameters = new ArrayList<>();
        parameters.add(getCoup("id", "SERIAL PRIMARY KEY"));
        parameters.add(getCoup("email", "TEXT UNIQUE"));
        parameters.add(getCoup("password", "TEXT"));
        parameters.add(getCoup("name", "TEXT"));
        parameters.add(getCoup("surname", "TEXT"));
        parameters.add(getCoup("age", "INT"));
        parameters.add(getCoup("balance", "INT"));
        parameters.add(getCoup("ava", "TEXT"));
        return parameters;
    }

    public static ArrayList<ArrayList<String>> getParametersUsersAndPhotos() {
        ArrayList<ArrayList<String>> parameters = new ArrayList<>();
        parameters.add(getCoup("userId", "TEXT"));
        parameters.add(getCoup("photoId", "TEXT"));
        return parameters;
    }

    public static ArrayList<String> getCoup(String a, String b) {
        ArrayList<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}
