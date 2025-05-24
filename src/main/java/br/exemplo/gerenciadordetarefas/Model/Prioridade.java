package br.exemplo.gerenciadordetarefas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Prioridade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;

}
