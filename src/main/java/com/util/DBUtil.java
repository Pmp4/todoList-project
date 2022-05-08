package com.util;

import java.sql.*;

public class DBUtil {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver 연결 실패");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@pmp4.iptime.org:1521:xe";
        return DriverManager.getConnection(url, "todoList", "@Parkks010420");
    }

    public static Connection getConnection(String user, String pwd)
            throws SQLException {
        String url = "jdbc:oracle:thin:@pmp4.iptime.org:1521:xe";
        return DriverManager.getConnection(url, user, pwd);
    }

    public static Connection getConnection(String url, String user, String pwd)
            throws SQLException {
        return DriverManager.getConnection(url, user, pwd);
    }

    public static void dbClose(ResultSet rs, PreparedStatement ps, Connection con)
            throws SQLException {
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
    }
    public static void dbClose(PreparedStatement ps, Connection con)
            throws SQLException {
        if(ps != null) ps.close();
        if(con != null) con.close();
    }
}
