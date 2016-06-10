/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeroen
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.Login", query = "SELECT username, password FROM users WHERE user_id :userid"),
    @NamedQuery(name = "Users.Register", query = "insert into users (username, password) value(:username, :password)")})
public class DbLogin implements Serializable {

    DbConnector db = new DbConnector();

    public EntityManager em;
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int Login(String username, String password) {
        String saltPassword = password + "Salted";
        int Result = 0;
        int Answer = 0;
        try {

            Statement stmt = db.con.createStatement();
            String SQL = "SELECT user_id FROM users WHERE username =" + username + "AND password = " + saltPassword + ";";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Result = rs.getInt("user_id");
            }
            if (rs.wasNull()) {
                Answer = 0;
            } else {
                Answer = Result;
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return Answer;

    }

    public int Register(String username, String password) {
        String saltPassword = password + "Salted";
        int Result = 0;
        int Answer = 0;
        try {

            Statement stmt = db.con.createStatement();
            String SQL = "insert into users (username, password) values (" + username + "," + saltPassword + ";";
            stmt.executeQuery(SQL);
            Answer = Login(username, password);

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

        return Answer;
    }
}
