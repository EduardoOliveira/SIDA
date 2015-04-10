package pt.iscte.sida.registo.gui;


import pt.iscte.sida.registo.controllers.CtlRegistoAlunos;
import pt.iscte.sida.registo.dObjects.Curso;
import pt.iscte.sida.registo.database.ServicoEmail;

import javax.swing.*;

/**
 * Created by Admin on 11-03-2015.
 */
public class EcraRegistoAlunos {
    private JPanel panel1;
    private JButton registarAlunoButton;
    private JButton cancelarRegistoButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox masculinoCheckBox;
    private JCheckBox femininoCheckBox;

    private JFrame frame;

    //Controlador
    private CtlRegistoAlunos ctlRegisto;
    private boolean verifiedEmail;

    //Atributos
    private String nome;
    private Curso curso;
    private String email;
    private int idade;
    private String sexo;

    public EcraRegistoAlunos(){
        frame = new JFrame("EcraRegistoAlunos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ctlRegisto = new CtlRegistoAlunos(); //Instanciação do controlador de Registo de Alunos
    }

    public void cancelarRegisto(){
        ctlRegisto.cancelarRegisto();
        frame.setVisible(false);

    }
    public void setCurso(String curso){
        if(!curso.equals("Nenhum")){
            ctlRegisto.setCurso(curso);
        }
    }
    public void setEmail(String email){

        ctlRegisto.verificaEmailIscte(email, nome);
        if(!verifiedEmail){ // Nao existe atributo verifiedEmail para saber o se esta a true ou a false
            displayMessage("Email não pertence ao iscte");
        }else{
            ctlRegisto.setEmail(email);
        }
    }
    public void setIdade(int idade){
        if(idade > 0){
            ctlRegisto.setIdade(idade);

        }

    }
    public void setNome(String nome){
        if(nome!= null){
            ctlRegisto.setNome(nome);
        }
    }
    public void setSexo(){

        ctlRegisto.setSexo(sexo);
    }
    public void setVerifyEmail(boolean b){
        this.verifiedEmail = b;

    }
    public void submeterRegisto(){
        ctlRegisto.submeterRegisto(nome, email, curso, idade, sexo);


    }
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }









}
