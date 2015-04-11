package pt.iscte.sida.registo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Admin on 11-03-2015.
 */
public class DBConnection {

    private String dbName;
    private String hostname;
    private int port;
    private String protocol = "jdbc";




    //Constutor variante
    //public DBConnection(String db, String user, String pw, )

    public DBConnection(String dbName, String hostname, int port, String protocol){
        this.dbName = dbName;
        this.hostname = hostname;
        this.port = port;
        this.protocol = protocol;
    }

    public void connect(String dbName, String hostname, int port, String protocol){

    }
    public int delete(String query){
        return 0;
    }

    // diagrama diz que metodo init é void
    public boolean init(){
        return true;
    }

    public int insert(String query){
        return 0;
    }

    public ResultSet select(String query){
        return null;
    }
    public int update(String query){
        return 0;
    }
}
