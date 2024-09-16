package br.com.tiagooliveira.medicalconsult.consulta.repositories;

import br.com.tiagooliveira.medicalconsult.consulta.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
}
