package ru.kpfu.servlets;

import java.util.ArrayList;
import java.util.List;

public interface DBHelperInterface {
    boolean getConnection();
    boolean closeConnection();
    boolean executeRequest(String request);

    boolean createTable(String tableName, ArrayList<ArrayList<String>> parameters);
    boolean dropTable(String tableName);

    boolean isExist(String tableName, ArrayList<String> keyAndValue);
    boolean addEntry(String tableName, ArrayList<ArrayList<String>> entry);
    User getUserBy(String tableName, int id);
    boolean updateUserFieldById(String tableName, int id, ArrayList<String> fieldAndValue);
    List<String> getColumnEntries(String tableName, String column);

//    int getTableSize(String tableName);
}
