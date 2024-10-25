package br.com.tiagooliveira.medicalconsult.consulta.services;

import br.com.tiagooliveira.medicalconsult.consulta.domain.Consulta;
import br.com.tiagooliveira.medicalconsult.consulta.repositories.ConsultaRepository;
import br.com.tiagooliveira.medicalconsult.exception.ExceptionDataIntegrityViolation;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void cadastrarConsulta() {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(null);  // ID deve ser nulo ao cadastrar

        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);

        Consulta resultado = consultaService.cadastrarConsulta(consulta);

        assertNotNull(resultado);
        assertNull(resultado.getIdConsulta());

        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void listarConsultas() {
        Consulta consulta1 = new Consulta();
        consulta1.setIdConsulta(1L);
        Consulta consulta2 = new Consulta();
        consulta2.setIdConsulta(2L);

        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        when(consultaRepository.findAll()).thenReturn(consultas);

        List<Consulta> resultado = consultaService.listarConsultas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getIdConsulta());
        assertEquals(2L, resultado.get(1).getIdConsulta());
    }

    @Test
    void buscarConsultaExistente() {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1L);

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta resultado = consultaService.buscarConsulta(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdConsulta());
    }

    @Test
    void buscarConsultaInexistente() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> consultaService.buscarConsulta(1L));
    }

    @Test
    void deletarConsultaExistente() {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(1L);

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        doNothing().when(consultaRepository).deleteById(1L);

        consultaService.deletarConsulta(1L);

        verify(consultaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletarConsultaComRelacionamento() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(new Consulta()));
        doThrow(DataIntegrityViolationException.class).when(consultaRepository).deleteById(1L);

        assertThrows(ExceptionDataIntegrityViolation.class, () -> consultaService.deletarConsulta(1L));
    }

    @Test
    void atualizarConsulta() throws ParseException {
        Consulta consultaExistente = new Consulta();
        consultaExistente.setIdConsulta(1L);
        consultaExistente.setDataConsulta(new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2024")); // Data como Date

        Consulta consultaAtualizada = new Consulta();
        consultaAtualizada.setIdConsulta(1L);
        consultaAtualizada.setDataConsulta(new SimpleDateFormat("dd-MM-yyyy").parse("02-10-2024")); // Data como Date

        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consultaExistente));
        when(consultaRepository.save(consultaExistente)).thenReturn(consultaExistente);

        Consulta resultado = consultaService.atualizarConsulta(consultaAtualizada);

        assertNotNull(resultado);
        assertEquals(consultaAtualizada.getDataConsulta(), resultado.getDataConsulta()); // Verifica se a data foi atualizada
    }
}
