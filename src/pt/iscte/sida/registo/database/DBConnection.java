package pt.iscte.sida.registo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBConnection {

    private String dbName;
    private String hostname;
    private int port;
    private String protocol;
    private Connection conn;

    private static final String USER = "sida";
    private static final String PW = "sida123";

    public DBConnection(String dbName, String hostname, int port, String protocol) {
        this.dbName = dbName;
        this.hostname = hostname;
        this.port = port;
        this.protocol = protocol;
        init();
    }

    public void connect(String dbName, String hostname, int port, String protocol){
        throw new UnsupportedOperationException("Not Implemented");
    }

    public int delete(String query){
        int result = -1;
        try {
            result = conn.createStatement().executeUpdate(query);
        } catch (Exception e){
            return result;
        }
        return result;
    }

    // diagrama diz que metodo init é void
    public boolean init(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlanywhere:uid="+USER+";pwd="+PW+";eng=sida");
        } catch (Exception e) {
            System.out.println("Server down, unable to make the connection. ");
            return false;
        }
        return true;
    }

    public int insert(String query){
        int result = -1;
        try {
            result = conn.createStatement().executeUpdate(query);
        } catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public ResultSet select(String query){
        try {
            return conn.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(String query) {
        int result = -1;
        try {
            result = conn.createStatement().executeUpdate(query);
        } catch (Exception e){
            return result;
        }
        return result;
    }
}
