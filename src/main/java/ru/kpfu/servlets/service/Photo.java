package ru.kpfu.servlets.service;

import java.util.ArrayList;
import java.util.Objects;

public class Photo {
    private int id;
    private String path;
    private String name;
    private String description;
    private int price;
    private int canBuy; // (-1) - infinitely | (n >= 1) - so many times you can buy
    private int purchasedCount;

    public Photo(String path, String name, String description, int price, int canBuy, int purchasedCount) {
        this.path = path;
        this.name = name;
        this.description = description;
        this.price = price;
        this.canBuy = canBuy;
        this.purchasedCount = purchasedCount;
    }

    public Photo(String path) {
        this.path = path;

        this.name = "Hello";
        this.description = "Hello";
        this.price = 1000;
        this.canBuy = 100;
        this.purchasedCount = 5;
    }

    public Photo(ArrayList<String> data) {
        int i = 0;
        this.id = Integer.parseInt(data.get(i++));
        this.path = data.get(i++);
        this.name = data.get(i++);
        this.description = data.get(i++);
        this.price = Integer.parseInt(data.get(i++));
        this.canBuy = Integer.parseInt(data.get(i++));
        this.purchasedCount = Integer.parseInt(data.get(i++));
    }

    public Photo() {
        this.path = "";
        this.name = "";
        this.description = "";
        this.price = 0;
        this.canBuy = 0;
        this.purchasedCount = 0;
    }

    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(User.getCoup("path", path));
        data.add(User.getCoup("name", name));
        data.add(User.getCoup("description", description));
        data.add(User.getCoup("price", Integer.toString(price)));
        data.add(User.getCoup("canBuy", Integer.toString(canBuy)));
        data.add(User.getCoup("purchasedCount", Integer.toString(purchasedCount)));
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
        parameters.add(User.getCoup("purchasedCount", "INT"));
        return parameters;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(int canBuy) {
        this.canBuy = canBuy;
    }

    public int getPurchasedCount() {
        return purchasedCount;
    }

    public void setPurchasedCount(int purchasedCount) {
        this.purchasedCount = purchasedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id && price == photo.price && canBuy == photo.canBuy && purchasedCount == photo.purchasedCount && Objects.equals(path, photo.path) && Objects.equals(name, photo.name) && Objects.equals(description, photo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, name, description, price, canBuy, purchasedCount);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", canBuy=" + canBuy +
                ", purchasedCount=" + purchasedCount +
                '}';
    }
}
