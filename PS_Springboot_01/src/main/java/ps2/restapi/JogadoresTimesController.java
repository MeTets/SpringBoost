package ps2.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JogadoresTimesController {
    
    private List <Jogadores> jogadores;
    private List<Time> times;

    public JogadoresTimesController(){
        this.jogadores = new ArrayList<>();
        this.times = new ArrayList<>();
        jogadores.add(new Jogadores(1, "Mateus", 20));
    }

    @GetMapping("/api/jogadores")
    Iterable<Jogadores> getJogadores(){
        return this.jogadores;
    }

    @GetMapping("/api/jogadores/{id}")
    Optional<Jogadores> getJogador(@PathVariable long id) {
        for(Jogadores j: jogadores){
            if(j.getId() == id)
                return Optional.of(j);
        }
        return Optional.empty();

    }

    @PostMapping("/api/jogadores")
    Jogadores createJogadores(@RequestBody Jogadores j){
        long maxId = 1;
        for(Jogadores jog: jogadores){
            if(jog.getId() > maxId){
                maxId = jog.getId();
            }
        }
        j.setId(maxId + 1);
        jogadores.add(j);
        return j;
    }

    @PutMapping("/api/jogadores/{JogadorID}")
    Optional<Jogadores> updateJogadores(@RequestBody Jogadores JogadoresRequest, @PathVariable long JogadorID){
        Optional<Jogadores> aux = this.getJogador(JogadorID);
        if(aux.isPresent()){
            Jogadores Jogador = aux.get();
            Jogador.setNome(JogadoresRequest.getNome());
            Jogador.setIdade(JogadoresRequest.getIdade());
        }

        return aux;
    }

    @DeleteMapping(value = "/api/jogadores/delete/{id}")
    void DeleteJogar(@PathVariable long id){
        jogadores.removeIf(j -> j.getId() == id);
    }

    @GetMapping("/api/time")
    Iterable<Time> getTimes(){
        return this.times;
    }

    @GetMapping("/api/time/{nome}")
    Optional<Time> getTime(@PathVariable String nome){
        for(Time t: times){
            if(t.getNome() == nome){
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/api/time/{nome}/jogadores")
    Iterable<Jogadores> getJogadoresTime(@PathVariable String nome){
        Optional<Time> opt = this.getTime(nome);
        if(opt.isPresent()){
            Time time = opt.get();
            return time.getJogadores();
        }
        return null;
    }

    @PostMapping("/api/time")
    Time createTime(@RequestBody Time t){
        String add = "1";
        String nomeMax = t.getNome();
        for(Time tim: times){
            if(nomeMax.equals(tim.getNome())){
                nomeMax += add;
            }
        }
        t.setNome(nomeMax);
        times.add(t);
        return t;
    }

    @PostMapping("/api/time/{nome}/jogadores/{idJogador}")
    Optional<Jogadores> addJogador(@PathVariable String nome, @PathVariable long id){
        Optional<Time> opt = this.getTime(nome);
        Optional<Jogadores> opt2 = this.getJogador(id);
        if(opt.isPresent()){
            if(opt2.isPresent()){
                Time time1 = opt.get();
                Jogadores jogador1 = opt2.get();
                time1.addJogadores(jogador1);
                return Optional.of(jogador1);
            }

            return Optional.empty();
        }

        return Optional.empty();
    }

    @DeleteMapping("/api/time/{nome}")
    void deleteTime(@PathVariable String nome){
        times.removeIf(t -> t.getNome() == nome);
    }

    @DeleteMapping("/api/time/{nome}/jogadores/{id}")
    void deleteJogador(@PathVariable String nome, @PathVariable long id){
        Optional<Time> opt = this.getTime(nome);
        if(opt.isPresent()){
            Time time1 = opt.get();
            List<Jogadores> aux = time1.getJogadores();
            aux.removeIf(j -> j.getId() == id);
        }
    }

}
