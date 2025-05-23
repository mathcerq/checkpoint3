package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import br.com.fiap.checkpoint3.repository.ConsultaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;



import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private ConsultaRepository consultaRepository;


    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public Paciente cadastrar(@RequestBody Paciente paciente) {
        return repository.save(paciente);
    }

    @GetMapping
    public List<Paciente> listar(@RequestParam(required = false, defaultValue = "asc") String sort) {
        return sort.equalsIgnoreCase("desc")
                ? repository.findAll(org.springframework.data.domain.Sort.by("nome").descending())
                : repository.findAll(org.springframework.data.domain.Sort.by("nome").ascending());
    }
    @GetMapping("/{id}/consultas")
    public List<Consulta> consultasDoPaciente(
            @PathVariable Long id,
            @RequestParam StatusConsulta status,
            @RequestParam(name = "data_de") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDe,
            @RequestParam(name = "data_ate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAte
    ) {
        return consultaRepository.findByPacienteIdAndStatusConsultaAndDataConsultaBetween(
                id,
                status,
                dataDe,
                dataAte
        );
    }


    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Paciente atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        return repository.save(paciente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
