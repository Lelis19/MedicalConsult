package br.com.tiagooliveira.medicalconsult.consulta.resources;


import br.com.tiagooliveira.medicalconsult.consulta.domain.Consulta;
import br.com.tiagooliveira.medicalconsult.consulta.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaResource {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta){
        Consulta novaConsulta = consultaService.cadastrarConsulta(consulta);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri().path("/id")
                    .buildAndExpand(novaConsulta.getIdConsulta()).toUri();
            return ResponseEntity.created(uri).body(novaConsulta);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarUsuarios(){
        return ResponseEntity.ok().body(consultaService.listarConsultas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> buscarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarConsulta(id);
        return ResponseEntity.ok().body(consulta);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@RequestBody Consulta consulta, @PathVariable Long Id){
        consulta.setIdConsulta(Id);
        Consulta consultaAtualizada = consultaService.atualizarConsulta(consulta);
        return ResponseEntity.ok().body(consultaAtualizada);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
