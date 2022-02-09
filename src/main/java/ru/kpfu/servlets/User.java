package ru.kpfu.servlets;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private int gender;
    private int dateBirth;
    private String email;
    private String password;
    private int balance;
    private String ava;

    public User(String name, String surname, String patronymic, int gender, int dateBirth, String email, String password, int balance, String ava) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.ava = ava;
    }

    public User() {
        this.name = "";
        this.surname = "";
        this.patronymic = "";
        this.gender = 0;
        this.dateBirth = 0;
        this.email = "";
        this.password = "";
        this.balance = 0;
        this.ava = "";
    }

    public ArrayList<ArrayList<String>> getParam() {
        ArrayList<ArrayList<String>> param = new ArrayList<>();
        param.add(getCoup("id", "SERIAL PRIMARY KEY"));
        param.add(getCoup("name", "TEXT"));
        param.add(getCoup("surname", "TEXT"));
        param.add(getCoup("patronymic", "TEXT"));
        param.add(getCoup("gender", "INT"));
        param.add(getCoup("dateBirth", "INT"));
        param.add(getCoup("email", "TEXT UNIQUE"));
        param.add(getCoup("password", "TEXT"));
        param.add(getCoup("balance", "INT"));
        param.add(getCoup("ava", "TEXT"));
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
        data.add(getCoup("name", name));
        data.add(getCoup("surname", surname));
        data.add(getCoup("patronymic", patronymic));
        data.add(getCoup("gender", Integer.toString(gender)));
        data.add(getCoup("dateBirth", Integer.toString(dateBirth)));
        data.add(getCoup("email", email));
        data.add(getCoup("password", password));
        data.add(getCoup("balance", Integer.toString(balance)));
        data.add(getCoup("ava", ava));
        return data;
    }

    public void setRandomData() {
        name = getRandomStr();
        surname = getRandomStr();
        patronymic = getRandomStr();
        email = getRandomStr();
        password = getRandomStr();
        ava = getRandomStr();
        dateBirth = getRandomInt();
        balance = getRandomInt();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(int dateBirth) {
        this.dateBirth = dateBirth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && gender == user.gender && dateBirth == user.dateBirth && balance == user.balance && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(patronymic, user.patronymic) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(ava, user.ava);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, gender, dateBirth, email, password, balance, ava);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", gender=" + gender +
                ", dateBirth=" + dateBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", ava='" + ava + '\'' +
                '}';
    }
}
