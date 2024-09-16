package br.com.tiagooliveira.medicalconsult.consulta.domain;

import br.com.tiagooliveira.medicalconsult.usuario.domain.Usuario;
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

}
