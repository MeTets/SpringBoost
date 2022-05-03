package ps2.restapi;

public class Jogadores {
    private long id;
    private String nome;
    private int idade;

    public Jogadores(){}
    public Jogadores(long id, String nome, int idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }
}
