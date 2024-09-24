package br.com.tiagooliveira.medicalconsult.usuario.domain;

import br.com.tiagooliveira.medicalconsult.consulta.domain.Consulta;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_USUARIO")
    private long idUsuario;
    @Column(name= "NOMEUSUARIO")
    private String nomeUsuario;
    @Column(name= "EMAIL")
    private String email;
    @Column(name= "CPF")
    private String cpf;
    @Column(name= "TELEFONE")
    private String telefone;
    @Column(name= "DATANASCIMENTO")
    private Date dataNascimento;
    @Column(name = "PERMISSAO")
    private Permissao permissao;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    public Usuario(long idUsuario, String nomeUsuario, String email, String cpf, String telefone, Date dataNascimento, Permissao permissao) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.permissao = permissao;
    }

    public Usuario(){
    }

}
