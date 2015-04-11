package pt.iscte.sida.registo.gui;


import pt.iscte.sida.registo.controllers.CtlRegistoAlunos;
import pt.iscte.sida.registo.dObjects.Curso;
import pt.iscte.sida.registo.database.ServicoEmail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcraRegistoAlunos {
    private JPanel panel1;
    private JButton registarAlunoButton;
    private JButton cancelarRegistoButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;

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

   private ButtonGroup buttonsGroup = new ButtonGroup();

    public EcraRegistoAlunos(){
        frame = new JFrame("EcraRegistoAlunos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ctlRegisto = new CtlRegistoAlunos(); //Instanciação do controlador de Registo de Alunos
        Curso[] cursos = ctlRegisto.getCursos();

        buttonsGroup.add(masculinoRadioButton);
        buttonsGroup.add(femininoRadioButton);

        comboBox1.setModel(new DefaultComboBoxModel<>(cursos));
        //Adicionar curso a combo box



        registarAlunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(masculinoRadioButton.isSelected()){
                    setSexo(masculinoRadioButton.getText());
                }
                if(femininoRadioButton.isSelected()){
                    setSexo(femininoRadioButton.getText());
                }
                setNome(textField1.getText());
                setEmail(textField2.getText());
                setIdade(Integer.valueOf(textField4.getText()));

                submeterRegisto();
            }
        });

        cancelarRegistoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarRegisto();
            }
        });

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
        if(!verifiedEmail){
            displayMessage("Email não pertence ao iscte");
        }else{
            ctlRegisto.setEmail(email);
            this.email = email;
        }
    }
    public void setIdade(int idade){
        if(idade > 0){
            ctlRegisto.setIdade(idade);
            this.idade =idade;
        }

    }
    public void setNome(String nome){
        if(nome!= null){
            ctlRegisto.setNome(nome);
            this.nome =nome;
        }
    }

    //Na especificaçao este metodo nao recebe nada
    public void setSexo(String sexo){
        this.sexo = sexo;
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
