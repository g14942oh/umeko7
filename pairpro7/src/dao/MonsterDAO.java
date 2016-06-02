/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import model.Monster;

/**
 *
 * @author g14925mm
 */
public class MonsterDAO {
    private final String DRIVER_NAME = "org.apache.derby.jdbc.ClientDriver"; //"org.h2.Driver";
    private final String JDBC_URL = "jdbc:derby://localhost:1527/db25541"; // "jdbc:h2:file:C:/data/docoTsubu";
    private final String DB_USER = "db";
    private final String DB_PASS = "db";

    public Monster testmonster() {
    	//Monster monster = new Monster();
    	 String name = "monster";
         int atk = 10;
         int hp = 20;
         Monster monster = new Monster(name,atk,hp);
         return monster;
    }
    public Monster findBymonster() {
        Monster monster = new Monster();
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            Random rd = new Random();
            int n = rd.nextInt(3) + 1;
            String sql = "SELECT * FROM MONSTER WHERE NUMBER = ?";
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,n);
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()) {
                String name = rs.getString("NAME");
                int atk = rs.getInt("ATK");
                int hp = rs.getInt("HP");
                monster = new Monster(name,atk,hp);
                //System.out.println("ATK = "+atk+",HP = "+hp);
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return monster;
    } 
}
