package ps2.restapi;

import java.util.*;

public class Time {
    private String nome;
    private String cidade;
    private List <Jogadores> time;

    public Time(){}

    public Time(String nome, String cidade){
        this.nome = nome;
        this.cidade = cidade;
        time = new ArrayList<>();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public List<Jogadores> getJogadores(){
        return time;
    }

    public void addJogadores(Jogadores j){
        time.add(j);
    }

    public String getNome(){
        return nome;
    }

    public String getCidade(){
        return cidade;
    }


}



