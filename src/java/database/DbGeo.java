/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "geo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geo.randomSave", query = "SELECT * FROM tussentabel u WHERE u.username = :username"),
    @NamedQuery(name = "Geo.save", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class DbGeo implements Serializable {

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

    public List getListByUserId(String userId) {
        return null;

    }
    
}