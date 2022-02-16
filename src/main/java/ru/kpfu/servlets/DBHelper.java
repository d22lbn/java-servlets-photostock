package ru.kpfu.servlets;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper implements DBHelperInterface {
    private Connection connection;
    private Statement statement;

    private final String url;
    private final String user;
    private final String password;

    public DBHelper() {
        url = "jdbc:postgresql://localhost:5432/photostock";
        user = "postgres";
        password = "fktif2002";

        getConnection();
    }

    @Override
    public boolean getConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean closeConnection() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean executeRequest(String request) {
        try {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean createTable(String tableName, ArrayList<ArrayList<String>> parameters) {
        if (parameters.size() == 0) {
            return false;
        }
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        for (int i = 0; i < parameters.size(); i++) {
            sql.append(parameters.get(i).get(0)).append(" ").append(parameters.get(i).get(1));
            if (i < parameters.size() - 1) {
                sql.append(", ");
            } else {
                sql.append(")");
            }
        }
        executeRequest(sql.toString());
        return true;
    }

    @Override
    public boolean dropTable(String tableName) {
        executeRequest("DROP TABLE " + tableName);
        return true;
    }

    @Override
    public boolean isExist(String tableName, ArrayList<String> keyAndValue) {
        if (keyAndValue.size() == 0) {
            return false;
        }
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM " + tableName + " WHERE " +
                keyAndValue.get(0) + " = '" + keyAndValue.get(1) + "'");
        int count = 0;
        try {
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                count++;
                if (count > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (count != 0);
    }

    @Override
    public boolean addEntry(String tableName, ArrayList<ArrayList<String>> entry) {
        if (entry.size() == 0) {
            return false;
        }
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 0; i < entry.size(); i++) {
            sql.append(entry.get(i).get(0));
            if (i < entry.size() - 1) {
                sql.append(", ");
            } else {
                sql.append(") VALUES ('");
            }
        }
        for (int i = 0; i < entry.size(); i++) {
            sql.append(entry.get(i).get(1));
            if (i < entry.size() - 1) {
                sql.append("', '");
            } else {
                sql.append("')");
            }
        }
        executeRequest(sql.toString());
        return true;
    }

    @Override
    public User getUserBy(String tableName, int id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE id = '" + id + "'");
        ArrayList<String> data = new ArrayList<>();
        ArrayList<ArrayList<String>> userParameters = User.getParameters();
        try {
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                for (ArrayList<String> userParameter : userParameters) {
                    data.add(rs.getString(userParameter.get(0)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (new User(data));
    }

    @Override
    public boolean updateUserFieldById(String tableName, int id, ArrayList<String> fieldAndValue) {
        if (fieldAndValue.size() == 0) {
            return false;
        }
        String sql = "UPDATE " + tableName + " SET " +
                fieldAndValue.get(0) + " = '" + fieldAndValue.get(1) + "' WHERE id = " + id;
        executeRequest(sql);
        return true;
    }

    @Override
    public ArrayList<String> getColumnEntries(String tableName, String column) {
        StringBuilder sql = new StringBuilder("SELECT " + column + " FROM " + tableName);
        ArrayList<String> data = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()){
                data.add(rs.getString(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
