package pt.iscte.sida.registo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBIscte_iul {


    private Connection conn;

    public DBIscte_iul(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlanywhere:uid=iscte;pwd=iscte123;eng=iscte");
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
