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


    public CtlRegistoAlunos(EcraRegistoAlunos gui) {
        this.gui = gui;
        dbConnection = new DBConnection("sida", "localhost", 2638, "jdbc:sqlanywhere:Tds:");
        // a parametros de base de dados
    }

    public void cancelarRegisto() {
        System.exit(0);

    }

    public Curso[] getCursos() {
        Curso curso = new Curso(); //?
        Curso[] c = curso.selectAllCurso();

        estudante = new Estudante();
        return c;
    }

    public void setCurso(String curso) {
        estudante.setCurso(curso);
    }

    public void setEmail(String email) {
        ResultSet rs = dbConnection.select("Select Email_Aluno from Estudante where Estudante.Email_Aluno = '" + email + "'");
        try {
            if (!rs.next()) {
                estudante.setEmail(email);
            } else {
                gui.displayMessage("Email j� est� registado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdade(int idade) {
        estudante.setIdade(idade);
    }

    public void setNome(String nome) {
        estudante.setNome(nome);
    }

    public void setSexo(String sexo) {
        estudante.setSexo(sexo);
    }

    public String pass() {
        byte[] result = new byte[0];
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            //generate a random number
            String randomNum = new Integer(prng.nextInt()).toString();

            //get its digest
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            result = sha.digest(randomNum.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return hexEncode(result);
    }

    static private String hexEncode(byte[] aInput) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }


    public void submeterRegisto(String nome, String email, Curso curso, int idade, String sexo) {
        String senha = pass();
        int rtn =dbConnection.insert("INSERT INTO Estudante (Nome,Email_Aluno,Senha,Curso,Idade,Sexo) VALUES ('" + nome + "','" +
                email + "','" + senha + "','" + curso.getSiglaCurso() + "'," + idade + ",'" + sexo + "')");
        if (rtn != -1) {

            ServicoEmail sEmail = new ServicoEmail();
            sEmail.sendMessage("IUL_Quiz@noreply.com", email, "Registo Aluno", "A sua senha �: " + senha);
            gui.displayMessage("Email enviado com Sucesso!");
        }

    }


    public void verificaEmailIscte(String email, String nome) {
        DBIscte_iul dbIscte = new DBIscte_iul();
        ResultSet rs = dbIscte.select("SELECT nome, email from Utilizador where Utilizador.nome = '" + nome + "' and Utilizador.email = '" + email + "'");
        try {
            if (!rs.next()) {
                gui.setVerifyEmail(false);
            } else {
                gui.setVerifyEmail(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
