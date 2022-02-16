package ru.kpfu.servlets.service;

import ru.kpfu.servlets.service.User;

import java.util.ArrayList;
import java.util.List;

public interface DBHelperInterface {
    boolean getConnection();
    boolean closeConnection();
    boolean executeRequest(String request);

    boolean createTable(String tableName, ArrayList<ArrayList<String>> parameters);
    boolean dropTable(String tableName);

    boolean isExist(String tableName, String key, String value);
    boolean addEntry(String tableName, ArrayList<ArrayList<String>> entry);

    User getUserById(String tableName, int id);
    User getUserByEmail(String tableName, String email);

    boolean updateUserFieldById(String tableName, int id, ArrayList<String> fieldAndValue);
    List<String> getColumnEntries(String tableName, String column);

//    int getTableSize(String tableName);
}
