package br.com.tiagooliveira.medicalconsult.consulta.services;

import br.com.tiagooliveira.medicalconsult.consulta.domain.Consulta;
import br.com.tiagooliveira.medicalconsult.consulta.repositories.ConsultaRepository;
import br.com.tiagooliveira.medicalconsult.usuario.domain.Usuario;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta cadastrarConsulta(Consulta consulta) {
        consulta.setIdConsulta(null);
        return consultaRepository.save(consulta);
    }

    public Consulta buscarConsulta(Long id) {
        return consultaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Consulta não encontrado! ID: ", id));
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void deletarConsulta(Long id){
        Consulta consulta = buscarConsulta(id);
        consultaRepository.delete(consulta);
    }

    public Consulta atualizarConsulta(Consulta consulta, Long id) {
        Consulta upConsulta = buscarConsulta(id);
        upConsulta.setDataConsulta(consulta.getDataConsulta());
        upConsulta.setProfissional(consulta.getProfissional());
        upConsulta.setEspecialidade(consulta.getEspecialidade());
        return consultaRepository.save(upConsulta);
    }
}

