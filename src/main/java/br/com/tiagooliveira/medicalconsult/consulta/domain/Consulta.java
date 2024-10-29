package br.com.tiagooliveira.medicalconsult.consulta.domain;

import br.com.tiagooliveira.medicalconsult.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "CONSULTAS")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_CONSULTA")
    private Long idConsulta;
    @Column(name= "DATA_CONSULTA")
    private Date dataConsulta;
    @Column(name= "PROFISSIONAL")
    private String profissional;
    @Column(name= "ESPECIALIDADE")
    private String especialidade;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Consulta(){

    }
    public Consulta(Long idConsulta, Date dataConsulta, String profissional, String especialidade, Usuario usuario) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.profissional = profissional;
        this.especialidade = especialidade;
        this.usuario = usuario;
    }
}
