package pt.iscte.sida.registo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBIscte_iul {


    private static final String dbUrl = "jdbc:sqlanywhere:Tds:localhost:2638?eng=iscte";
    private static final String USER = "iscte";
    private static final String PW = "iscte123";
    private Connection conn;

    public DBIscte_iul(){
        try {
            conn = DriverManager.getConnection(dbUrl, USER, PW);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println("Server down, unable to make the connection. ");
        }
    }

    public ResultSet select(String query){

        try {
            return conn.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
