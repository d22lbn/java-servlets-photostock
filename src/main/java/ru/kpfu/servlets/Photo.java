package ru.kpfu.servlets;

import java.util.ArrayList;
import java.util.Objects;

public class Photo {
    private int id;
    private String name;
    private String path;

    public Photo(String name) {
        this.name = name;
        this.path = "photo/" + name;
    }

    public Photo() {
        this.name = "";
        this.path = "photo/" + name;
    }

    public ArrayList<ArrayList<String>> getParam() {
        ArrayList<ArrayList<String>> param = new ArrayList<>();
        param.add(getCoup("id", "SERIAL PRIMARY KEY"));
        param.add(getCoup("path", "TEXT"));
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
        return data;
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
        this.path = "photo/" + name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id && Objects.equals(name, photo.name) && Objects.equals(path, photo.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
