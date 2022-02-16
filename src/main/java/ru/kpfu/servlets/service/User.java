package ru.kpfu.servlets.service;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private int age;
    private int balance;
    private String ava;
    private String cookie;

    public User(String email, String password, String name, String surname, String cookie) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.cookie = cookie;

        this.age = 0;
        this.balance = 0;
        this.ava = "";
    }

    public User() {
        this.email = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.age = 0;
        this.balance = 0;
        this.ava = "";
        this.cookie = "";
    }

    public User(ArrayList<String> data) {
        int i = 0;
        this.id = Integer.parseInt(data.get(i++));
        this.email = data.get(i++);
        this.password = data.get(i++);
        this.name = data.get(i++);
        this.surname = data.get(i++);
        this.age = Integer.parseInt(data.get(i++));
        this.balance = Integer.parseInt(data.get(i++));
        this.ava = data.get(i++);
        this.cookie = data.get(i++);
    }

    public ArrayList<ArrayList<String>> getData() {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        data.add(getCoup("email", email));
        data.add(getCoup("password", password));
        data.add(getCoup("name", name));
        data.add(getCoup("surname", surname));
        data.add(getCoup("age", Integer.toString(age)));
        data.add(getCoup("balance", Integer.toString(balance)));
        data.add(getCoup("ava", ava));
        data.add(getCoup("cookie", cookie));
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
        parameters.add(getCoup("cookie", "TEXT"));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && balance == user.balance && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(ava, user.ava) && Objects.equals(cookie, user.cookie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, age, balance, ava, cookie);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", ava='" + ava + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
