//package ru.kpfu.servlets;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class DBHelper_02 {
//    private Connection connection;
//    private Statement statement;
//
//    private String url;
//    private String user;
//    private String password;
//
//    public DBHelper_02() {
//        connection = null;
//        statement = null;
//
//        url = "jdbc:postgresql://localhost:5432/photostock";
//        user = "postgres";
//        password = "fktif2002";
//    }
//
//    public boolean createTable(String tableName, ArrayList<ArrayList<String>> param) {
//        if (param.size() == 0) {
//            return false;
//        }
//        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
//        for (int i = 0; i < param.size(); i++) {
//            sql.append(param.get(i).get(0)).append(" ").append(param.get(i).get(1));
//            if (i < param.size() - 1) {
//                sql.append(", ");
//            } else {
//                sql.append(")");
//            }
//        }
//        if (getConnect()) {
//            System.out.println(sql.toString());
//            exec(sql.toString());
//            return true;
//        }
//        return false;
//    }
//
//    private boolean dropTable(String tableName) {
//        String sql = "DROP TABLE " + tableName;
//        if (getConnect()) {
//            exec(sql);
//            return true;
//        }
//        return false;
//    }
//
////    public boolean addUser(User u, String tableName) {
////        if (isExist(u.getEmail(), tableName)) return false;
////        String sql = "INSERT INTO " + tableName +
////                " (email, name, surname, password, gender) VALUES" +
////                " ('" + u.getEmail() + "', '" +
////                u.getName() + "', '" +
////                u.getSurname() + "', '" +
////                u.getPassword() + "', '" +
////                u.getGender() + "')";
////        if (getConnect()) {
////            exec(sql);
////            return true;
////        }
////        return false;
////    }
//
//    public boolean addData(String tableName, ArrayList<ArrayList<String>> data) {
//        if (data.size() == 0) {
//            return false;
//        }
//        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
//        for (int i = 0; i < data.size(); i++) {
//            sql.append(data.get(i).get(0));
//            if (i < data.size() - 1) {
//                sql.append(", ");
//            } else {
//                sql.append(") VALUES ('");
//            }
//        }
//        for (int i = 0; i < data.size(); i++) {
//            sql.append(data.get(i).get(1));
//            if (i < data.size() - 1) {
//                sql.append("', '");
//            } else {
//                sql.append("')");
//            }
//        }
//        if (getConnect()) {
//            System.out.println(sql.toString());
//            exec(sql.toString());
//            return true;
//        }
//        return false;
//    }
//
////    public boolean isExist(String email, String tableName) {
////        if (email == null) {
////            return false;
////        }
////        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE email = '" + email + "'";
////        int count = 0;
////        ResultSet rs = null;
////        try {
////            rs = statement.executeQuery(sql);
////            while (rs.next()){
////                count = rs.getInt(1);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return count != 0;
////    }
//
//    public boolean isExist(String tableName, ArrayList<String> el) {
//        if (el.size() == 0) {
//            return false;
//        }
//        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + el.get(0) + " = '" + el.get(1) + "'";
//        int count = 0;
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(sql);
//            while (rs.next()){
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count != 0;
//    }
//
//    public int getTableSize(String tableName) {
//        String sql = "SELECT COUNT(*) FROM " + tableName;
//        int size = 0;
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(sql);
//            while (rs.next()){
//                size = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return size;
//    }
//
//    public ArrayList<String> getSmth(String tableName, String smth) {
//        String sql = "SELECT " + smth + " FROM " + tableName;
//        ArrayList<String> data = new ArrayList<>();
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(sql);
//            while (rs.next()){
//                data.add(rs.getString(smth));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }
//
//    public User getUserDataByEmail(String tableName, String email) {
//        User user = new User();
//        String sql = "SELECT * FROM " + tableName + " WHERE email = '" + email + "'";
//        ArrayList<String> data = new ArrayList<>();
//        ArrayList<ArrayList<String>> userParam = User.getParam();
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(sql);
//            while (rs.next()){
//                for (int i = 0; i < userParam.size(); i++) {
//                    data.add(rs.getString(userParam.get(i).get(0)));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return (new User(data));
//    }
//
//    public boolean updateDataById(String tableName, String field, String fieldValue, String id) {
//        String sql = "UPDATE " + tableName + " SET " + field + " = '" + fieldValue + "' WHERE id = " + id;
//        if (getConnect()) {
//            exec(sql);
//            return true;
//        }
//        return false;
//    }
//
////    public String getPasswordByEmail(String em, String tableName) {
////        String email = em;
////        String sql = "SELECT password FROM " + tableName + " WHERE email = '" + email + "'";
////        String password = "";
////        ResultSet rs = null;
////        try {
////            rs = statement.executeQuery(sql);
////            while (rs.next()){
////                password += rs.getString("password");
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return password;
////    }
//
////    public List<List<String>> getUsers(String tableName) {
////        List<List<String>> users = new ArrayList<>();
////        List<String> columns = new ArrayList<>();
////
////        columns.add("id");
////        columns.add("почта");
////        columns.add("имя");
////        columns.add("фамилия");
////        columns.add("пароль");
////        columns.add("пол");
////        users.add(columns);
////
////        String sql = "SELECT * FROM " + tableName;
////        ResultSet rs = null;
////        try {
////            rs = statement.executeQuery(sql);
////            while (rs.next()){
////                List<String> l = new ArrayList<>();
////                ResultSetMetaData resultSetMetaData = rs.getMetaData();
////                StringBuilder row = new StringBuilder();
////                int count = resultSetMetaData.getColumnCount();
////                for (int i = 1; i <= count; i++) {
////                    int type = resultSetMetaData.getColumnType(i);
////                    if (type == Types.VARCHAR || type == Types.CHAR) {
////                        l.add(rs.getString(i));
////                    }
////                    else if (type == Types.INTEGER) {
////                        l.add(String.valueOf(rs.getInt(i)));
////                    }
////                    if (i < count) row.append(", ");
////                }
////                users.add(l);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return users;
////    }
//
//
//    public boolean getConnect() {
//        try {
//            Class.forName("org.postgresql.Driver").newInstance();
//            connection = DriverManager.getConnection(url, user, password);
//            try {
//                statement = connection.createStatement();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public void closeConnect() {
//        try {
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void exec(String s) {
//        try {
//            statement.execute(s);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
