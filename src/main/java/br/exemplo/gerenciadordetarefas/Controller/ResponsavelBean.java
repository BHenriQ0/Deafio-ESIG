package br.exemplo.gerenciadordetarefas.Controller;


import br.exemplo.gerenciadordetarefas.DAO.ReponsavelDAO;
import br.exemplo.gerenciadordetarefas.Model.Responsavel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.util.List;

@Data
@Named
@RequestScoped
public class ResponsavelBean {

    public List<Responsavel> responsavelCombo() {
        ReponsavelDAO reponsavelDAO = new ReponsavelDAO();
        return reponsavelDAO.buscarTodos();
    }
}
