package pt.iscte.sida.registo.dObjects;

import pt.iscte.sida.registo.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    //nao é recordSet mas sim resultSet
    //nome método nao standard
    public Curso[] ListFrom(ResultSet rs) throws SQLException {

        List<Curso> cursos = new ArrayList<>();
        while (rs.next()){
            cursos.add(new Curso(rs.getString("Designaca_Curso"), rs.getString("Sigla_Curso")));
        }
        Curso[]rtn = new Curso[cursos.size()];
        cursos.toArray(rtn);
        return rtn;
    }

    public Curso[] selectAllCurso(){
        //Tera de ser criada uma nova DB?

        DBConnection db = new DBConnection("sida","localhost",2638,"jdbc:sqlanywhere:Tds:");
        try {
            return ListFrom(db.select("Select * From Curso"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDesignacaoCurso() {
        return designacaoCurso;
    }

    public void setDesignacaoCurso(String designacaoCurso) {
        this.designacaoCurso = designacaoCurso;
    }

    public String getSiglaCurso() {
        return siglaCurso;
    }

    public void setSiglaCurso(String siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    @Override
    public String toString() {
        return designacaoCurso;
    }
}
