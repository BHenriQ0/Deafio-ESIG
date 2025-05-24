package br.exemplo.gerenciadordetarefas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Situacao {
    public static final Situacao SITUACAO_INICIAL = new Situacao(1, "Em andamento");
    public static final Situacao SITUACAO_CONCLUIDA = new Situacao(2, "Concluida");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;

    public Situacao() {

    }
}