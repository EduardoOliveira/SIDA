package pt.iscte.sida.registo.controllers;

import pt.iscte.sida.registo.dObjects.Curso;
import pt.iscte.sida.registo.dObjects.Estudante;
import pt.iscte.sida.registo.database.DBConnection;
import pt.iscte.sida.registo.database.DBIscte_iul;
import pt.iscte.sida.registo.database.ServicoEmail;
import pt.iscte.sida.registo.gui.EcraRegistoAlunos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 11-03-2015.
 */
public class CtlRegistoAlunos {

    //Database
    private DBConnection dbConnection;

    private Estudante estudante;

    private EcraRegistoAlunos gui;


    public CtlRegistoAlunos(){
        //dbConnection = new DBConnection(); //Controlador nao tem acesso
        // a parametros de base de dados
    }

    public void cancelarRegisto(){
        System.exit(0);

    }
    public Curso[] getCursos(){
        Curso curso = new Curso(); //?
        Curso[] c = curso.selectAllCurso();
        Estudante estudante = new Estudante();
        return c;
    }

    public void setCurso(String curso){
        estudante.setCurso(curso);
    }
    public void setEmail(String email){
        ResultSet rs = dbConnection.select("Select email from Estudante where Estudante.Email = " + email);
        try {
            if(!rs.next()){
                estudante.setEmail(email);
             }else{
                 gui.displayMessage("Email já está registado");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public String pass(){
        byte[] result = new byte[0];
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            //generate a random number
            String randomNum = new Integer(prng.nextInt()).toString();

            //get its digest
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            result =  sha.digest(randomNum.getBytes());
        }
        catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return hexEncode(result);
    }

    static private String hexEncode(byte[] aInput){
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[ (b&0xf0) >> 4 ]);
            result.append(digits[ b&0x0f]);
        }
        return result.toString();
    }


    public void submeterRegisto(String nome, String email, Curso curso, int idade, String sexo){
        String senha = pass();
        dbConnection.insert("Insert " + estudante + " into Estudante(" + nome +"," +
               email + "," + senha + "," + curso + "," + idade + "," + sexo);
        ServicoEmail sEmail = new ServicoEmail();
        sEmail.sendMessage("IUL_Quiz@noreply.com", email, "Registo Aluno", "A sua senha é: " + senha);

    }

    //Este metodo deveria receber o ecra
    public void verificaEmailIscte(String email, String nome){
        DBIscte_iul dbIscte = new DBIscte_iul();
        ResultSet rs = dbIscte.select("select Nome, Email from Utilizador where Utilizador.Nome =" +
                nome + " and Utilizador.Email = " + email);
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
