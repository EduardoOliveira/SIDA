package pt.iscte.sida.registo.dObjects;

import pt.iscte.sida.registo.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 11-03-2015.
 */
public class Curso {

    private String designacaoCurso;
    private String siglaCurso;



    //PARA APAGAR
    public Curso(){

    }

    public Curso(String designacaoCurso, String siglaCurso){
        this.designacaoCurso = designacaoCurso;
        this.siglaCurso = siglaCurso;
    }

    //TODO nao é recordSet mas sim resultSet
    //TODO nome método nao standard
    public Curso[] ListFrom(ResultSet rs) throws SQLException {

        Curso[] cursos;

        int size= 0;
        if (rs != null)
        {
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
        }

        cursos = new Curso[size];

        int i = 0;
        while (rs.next()){
            cursos[i] =  new Curso(rs.getString("designacao"), rs.getString("sigla"));
            i++;
        }

        return cursos;
    }

    public Curso[] selectAllCurso(){
        //Tera de ser criada uma nova DB?
        /*
        DBConnection db = new DBConnection();
        try {
            ListFrom(db.select("Select designacaoCurso From Curso"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        return null;
    }


}
