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
public class TimeController {
    
    private List<Time> times;

    public TimeController(){
        this.times = new ArrayList<>();
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
        Optional<Jogadores> opt2 = JogadoresController.getJogador(id);
        if(opt.isPresent()){
            Time time1 = opt.get();
            time1.addJogadores();
        }
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
