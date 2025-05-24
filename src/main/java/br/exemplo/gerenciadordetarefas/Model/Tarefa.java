package br.exemplo.gerenciadordetarefas.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String titulo;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "prioridade_id")
    private Prioridade prioridade;

    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "situacao_id")
    private Situacao situacao;

}
