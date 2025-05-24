package br.exemplo.gerenciadordetarefas.Controller;


import br.exemplo.gerenciadordetarefas.DAO.SituacaoDAO;
import br.exemplo.gerenciadordetarefas.Model.Situacao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.util.List;

@Data
@Named
@RequestScoped
public class SituacaoBean {

    public List<Situacao> situacaoCombo() {
        SituacaoDAO situacaoDAO = new SituacaoDAO();
        return situacaoDAO.buscarTodos();
    }

}
