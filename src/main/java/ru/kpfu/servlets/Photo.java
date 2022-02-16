package ru.kpfu.servlets;

import java.util.ArrayList;

public class Photo {
    private int id;
    private String path;
    private String name;
    private String description;
    private int price;
    private int canBuy; // (-1) - infinitely | (0) - can't buy | (n >= 1) - so many times you can buy

    public Photo(String path, String name, String description, int price, int canBuy) {
        this.path = path;
        this.name = name;
        this.description = description;
        this.price = price;
        this.canBuy = canBuy;
    }

    public Photo() {
        this.path = "";
        this.name = "";
        this.description = "";
        this.price = 0;
        this.canBuy = 0;
    }

//    public Photo(String name, String siteName, String description, int price, int canBuy) {
//        this.name = name;
//        this.path = "photo/" + name;
//        this.siteName = siteName;
//        this.description = description;
//        this.price = price;
//        this.canBuy = canBuy;
//    }

    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(User.getCoup("path", path));
        data.add(User.getCoup("name", name));
        data.add(User.getCoup("description", description));
        data.add(User.getCoup("price", Integer.toString(price)));
        data.add(User.getCoup("canBuy", Integer.toString(canBuy)));
        return data;
    }

    public static ArrayList<ArrayList<String>> getParameters() {
        ArrayList<ArrayList<String>> parameters = new ArrayList<>();
        parameters.add(User.getCoup("id", "SERIAL PRIMARY KEY"));
        parameters.add(User.getCoup("path", "TEXT UNIQUE"));
        parameters.add(User.getCoup("name", "TEXT"));
        parameters.add(User.getCoup("description", "TEXT"));
        parameters.add(User.getCoup("price", "INT"));
        parameters.add(User.getCoup("canBuy", "INT"));
        return parameters;
    }

}
