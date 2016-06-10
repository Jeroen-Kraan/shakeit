/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jeroen
 */
public class DbConnector {

    String userName;
    String password;
    Connection con;

    public DbConnector() {
        try {
            String host = "jdbc:mysql://localhost:3306/shakeitup";
            String uName = "root";
            String uPass = "";

            con = DriverManager.getConnection(host, uName, uPass);

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public String getAllUser() {
        String Result = null;
        try {

            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM users_has_location INNER JOIN users ON users_has_location.users_user_id = users.user_id WHERE users_has_location.users_user_id = 2";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int id = rs.getInt("users_user_id");
                String name = rs.getString("username");
                String ppassword = rs.getString("password");
                Result = id + name + ppassword;
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return Result;

    }
}
