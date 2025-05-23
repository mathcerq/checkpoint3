package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Profissional;
import br.com.fiap.checkpoint3.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import java.util.Map;
import java.util.HashMap;
import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;



import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private ConsultaRepository consultaRepository;

    @Autowired
    private ProfissionalRepository repository;

    @PostMapping
    public Profissional cadastrar(@RequestBody Profissional profissional) {
        return repository.save(profissional);
    }

    @GetMapping
    public List<Profissional> listar(@RequestParam(required = false, defaultValue = "asc") String sort) {
        return sort.equalsIgnoreCase("desc")
                ? repository.findAll(org.springframework.data.domain.Sort.by("nome").descending())
                : repository.findAll(org.springframework.data.domain.Sort.by("nome").ascending());
    }

    @GetMapping("/{id}")
    public Profissional buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Profissional atualizar(@PathVariable Long id, @RequestBody Profissional profissional) {
        profissional.setId(id);
        return repository.save(profissional);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
    @GetMapping("/{id}/consultas")
    public List<Consulta> consultasDoProfissional(
            @PathVariable Long id,
            @RequestParam StatusConsulta status,
            @RequestParam(name = "data_de") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDe,
            @RequestParam(name = "data_ate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAte
    ) {
        return consultaRepository.findByProfissionalIdAndStatusConsultaAndDataConsultaBetween(
                id,
                status,
                dataDe,
                dataAte
        );
    }

    @GetMapping("/{id}/stats")
    public Map<String, Object> stats(@PathVariable Long id) {
        Object[] stats = (Object[]) consultaRepository.getStatsByProfissional(id);

        Map<String, Object> response = new HashMap<>();
        response.put("totalConsultas", stats[0] != null ? stats[0] : 0);
        response.put("totalValor", stats[1] != null ? stats[1] : 0);
        response.put("totalHoras", stats[2] != null ? stats[2] : 0);

        return response;
    }
    

}

