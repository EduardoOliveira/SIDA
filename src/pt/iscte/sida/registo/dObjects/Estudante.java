package pt.iscte.sida.registo.dObjects;

/**
 * Created by Admin on 11-03-2015.
 */
public class Estudante {

    //TODO tipos de dados "varchar" não existentes em java
    private String curso;
    private String emailAluno;
    private int idade;
    private String nome;
    private String senha;
    private String sexo;

    public Estudante(){

    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEmail(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
