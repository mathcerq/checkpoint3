package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Consulta;
import br.com.fiap.checkpoint3.model.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Consultas de um profissional filtrando status e data
    List<Consulta> findByProfissionalIdAndStatusConsultaAndDataConsultaBetween(
            Long profissionalId,
            StatusConsulta status,
            LocalDate dataInicio,
            LocalDate dataFim
    );

    // Consultas de um paciente filtrando status e data
    List<Consulta> findByPacienteIdAndStatusConsultaAndDataConsultaBetween(
            Long pacienteId,
            StatusConsulta status,
            LocalDate dataInicio,
            LocalDate dataFim
    );

    // Consultas gerais por status e data
    List<Consulta> findByStatusConsultaAndDataConsultaBetween(
            StatusConsulta status,
            LocalDate dataInicio,
            LocalDate dataFim
    );

    // Consulta para pegar stats do profissional
    @Query("SELECT COUNT(c), SUM(c.valorConsulta), SUM(c.quantidadeHoras) " +
           "FROM Consulta c WHERE c.profissional.id = :profissionalId AND c.statusConsulta = 'REALIZADA'")
    Object getStatsByProfissional(Long profissionalId);
}


