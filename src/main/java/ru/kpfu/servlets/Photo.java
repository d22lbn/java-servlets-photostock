package ru.kpfu.servlets;

import java.util.ArrayList;
import java.util.Objects;

public class Photo {
    private int id;
    private String name;
    private String path;
    private String siteName;
    private String description;
    private int price;
    private int canBuy;

    public Photo(String name, String siteName, String description, int price, int canBuy) {
        this.name = name;
        this.path = "photo/" + name;
        this.siteName = siteName;
        this.description = description;
        this.price = price;
        this.canBuy = canBuy;
    }

    public Photo() {
        this.name = "";
        this.path = "photo/" + name;
        this.siteName = "";
        this.description = "";
        this.price = 0;
        this.canBuy = 0;
    }

    public ArrayList<ArrayList<String>> getParam() {
        ArrayList<ArrayList<String>> param = new ArrayList<>();
        param.add(getCoup("id", "SERIAL PRIMARY KEY"));
        param.add(getCoup("path", "TEXT UNIQUE"));
        param.add(getCoup("siteName", "TEXT"));
        param.add(getCoup("description", "TEXT"));
        param.add(getCoup("price", "INT"));
        param.add(getCoup("canBuy", "INT"));
        return param;
    }

    private ArrayList<String> getCoup(String a, String b) {
        ArrayList<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }

    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(getCoup("path", path));
        data.add(getCoup("siteName", siteName));
        data.add(getCoup("description", description));
        data.add(getCoup("price", Integer.toString(price)));
        data.add(getCoup("canBuy", Integer.toString(canBuy)));
        return data;
    }

    public void setRandomData() {
        name = getRandomStr();
        path = "photo/" + name;
        siteName = getRandomStr();
        description = getRandomStr();
        price = getRandomInt();
        canBuy = getRandomInt();
    }

    private String getRandomStr() {
        StringBuilder s = new StringBuilder();
        String arr = "qwertyuiopasdfghjklzxcvbnm";
        for (int i = 0; i < 4 + (int) (Math.random() * 3); i++) {
            String a = arr.charAt((int) (Math.random() * arr.length())) + "";
            if (i == 0) {
                a.toUpperCase();
            }
            s.append(a);
        }
        return s.toString();
    }

    private int getRandomInt() {
        return (int) (Math.random() * 100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id && price == photo.price && canBuy == photo.canBuy && Objects.equals(name, photo.name) && Objects.equals(path, photo.path) && Objects.equals(siteName, photo.siteName) && Objects.equals(description, photo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, siteName, description, price, canBuy);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", siteName='" + siteName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", canBuy=" + canBuy +
                '}';
    }
}
