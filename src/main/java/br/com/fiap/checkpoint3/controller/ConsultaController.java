package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    public Consulta cadastrar(@RequestBody Consulta consulta) {
        return repository.save(consulta);
    }

    @GetMapping
    public List<Consulta> listar() {
        return repository.findAll();
    }
    @GetMapping("/filtro")
    public List<Consulta> filtrarConsultas(
            @RequestParam StatusConsulta status,
            @RequestParam(name = "data_de") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDe,
            @RequestParam(name = "data_ate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAte
    ) {
        return repository.findByStatusConsultaAndDataConsultaBetween(
                status,
                dataDe,
                dataAte
        );
    }

    @GetMapping("/{id}")
    public Consulta buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Consulta atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        consulta.setId(id);
        return repository.save(consulta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

