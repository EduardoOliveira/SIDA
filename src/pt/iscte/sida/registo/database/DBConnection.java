package pt.iscte.sida.registo.database;

import java.sql.ResultSet;

/**
 * Created by Admin on 11-03-2015.
 */
public class DBConnection {

    private String dbName;
    private String hostname;
    private int port;
    private String protocol = "jdbc";

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

    //TODO descriçao diz que metodo init devolve boolean
    public void init(){

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
