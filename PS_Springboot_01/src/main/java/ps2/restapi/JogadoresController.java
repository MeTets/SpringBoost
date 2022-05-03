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
public class JogadoresController {
    
    private List <Jogadores> jogadores;

    public JogadoresController(){
        this.jogadores = new ArrayList<>();
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

}
