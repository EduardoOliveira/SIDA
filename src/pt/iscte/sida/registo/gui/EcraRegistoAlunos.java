package pt.iscte.sida.registo.gui;


import pt.iscte.sida.registo.controllers.CtlRegistoAlunos;
import pt.iscte.sida.registo.dObjects.Curso;

import javax.swing.*;
import java.util.regex.Pattern;

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

   private ButtonGroup buttonsGroup = new ButtonGroup();

    public EcraRegistoAlunos(){
        frame = new JFrame("EcraRegistoAlunos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ctlRegisto = new CtlRegistoAlunos(this); //Instanciação do controlador de Registo de Alunos
        Curso[] cursos = ctlRegisto.getCursos();

        buttonsGroup.add(masculinoRadioButton);
        buttonsGroup.add(femininoRadioButton);

        comboBox1.setModel(new DefaultComboBoxModel<>(cursos));
        //Adicionar curso a combo box



        registarAlunoButton.addActionListener(e -> {
            if(!masculinoRadioButton.isSelected()&&!femininoRadioButton.isSelected()){
                displayMessage("Selecione um sexo!!");
                return;
            }else{
                if(masculinoRadioButton.isSelected()){
                    setSexo(masculinoRadioButton.getText());
                }
                if(femininoRadioButton.isSelected()){
                    setSexo(femininoRadioButton.getText());
                }
            }

            if(textField1.getText().length()<=0 || textField1.getText().length()>200 ||
                    !(Pattern.matches("^[\\p{L} .'-]+$", textField1.getText()))){
                displayMessage("Nome Invalido");
                return;
            }
            if(textField2.getText().length()<=0 || textField2.getText().length()>500 ||
                    !(Pattern.matches("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$", textField2.getText()))){
                displayMessage("Email Invalido");
                return;
            }
            try{
                int idade = Integer.valueOf(textField4.getText());
                if(idade<0 || idade>120){
                    displayMessage("Idade Invalida!!");
                    return;
                }
                setIdade(idade);

            }catch(NumberFormatException ex){
                displayMessage("Idade Invalida!!");
                return;
            }

            submeterRegisto();
        });

        cancelarRegistoButton.addActionListener(e -> cancelarRegisto());

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

        ctlRegisto.verificaEmailIscte(email, textField1.getText());
        if(!verifiedEmail){
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

    //Na especificaçao este metodo nao recebe nada
    public void setSexo(String sexo){
        ctlRegisto.setSexo(sexo);
    }
    public void setVerifyEmail(boolean b){
        this.verifiedEmail = b;

    }
    public void submeterRegisto(){
        int idade=0;
        try{
            idade = Integer.parseInt(textField4.getText());

        }catch(NumberFormatException e){
            displayMessage("Idade Invalida!!");
            return;
        }

        ctlRegisto.submeterRegisto(textField1.getText(), textField2.getText(),
                (Curso)comboBox1.getSelectedItem(), idade,
                (femininoRadioButton.isSelected()?"feminino":((masculinoRadioButton.isSelected())?"masculino":"")));

    }
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }









}
