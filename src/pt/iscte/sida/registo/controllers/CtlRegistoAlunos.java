package pt.iscte.sida.registo.controllers;

import pt.iscte.sida.registo.dObjects.Curso;
import pt.iscte.sida.registo.dObjects.Estudante;
import pt.iscte.sida.registo.database.DBConnection;
import pt.iscte.sida.registo.database.DBIscte_iul;
import pt.iscte.sida.registo.gui.EcraRegistoAlunos;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 11-03-2015.
 */
public class CtlRegistoAlunos {

    //Database
    private DBConnection db;

    private Estudante estudante;

    private EcraRegistoAlunos gui;


    public CtlRegistoAlunos(){
        //dbConnection = new DBConnection(); //Controlador nao tem acesso
        // a parametros de base de dados
    }

    public void cancelarRegisto(){


    }
    public Curso[] getCursos(){
        return null;
    }

    public void setCurso(String curso){
        estudante.setCurso(curso);
    }
    public void setEmail(String email){
        // ResultSet rs = dbConnection.select("Select email from Estudante where Estudante.Email = email");
        // if(!rs.next()){
        //     setEmail(email);
        // }else{
        //     gui.displayMessage("Email já está registado");
        // }
    }
    public void setIdade(int idade){

        estudante.setIdade(idade);
    }
    public void setNome(String nome){
        Estudante estudante = new Estudante();
        estudante.setNome(nome);
    }
    public void setSexo(String sexo){

        estudante.setSexo(sexo);

    }
    public void submeterRegisto(String nome, String email, Curso curso, int idade, String sexo){
        //dbConnection.insert("Insert Estudante into Estudante(" + nome +"," +
        //         email + "," + senha + "," + curso + "," + idade + "," + sexo);
        // ServicoEmail sEmail = new ServicoEmail();
        // sEmail.sendMessage("IUL Quiz@noreply.com", email, "Registo Aluno", "A sua senha é: " + senha);

    }
    public void verificaEmailIscte(String email, String nome){
        DBIscte_iul dbIscte = new DBIscte_iul();
        ResultSet rs = dbIscte.select("select Nome, Email from Utilizador.Nome" +
                " = nome and Utilizador.Email = email");
        EcraRegistoAlunos gui = new EcraRegistoAlunos();
        try {
            if(!rs.next()){
                gui.setVerifyEmail(false);
            }else{
                gui.setVerifyEmail(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
