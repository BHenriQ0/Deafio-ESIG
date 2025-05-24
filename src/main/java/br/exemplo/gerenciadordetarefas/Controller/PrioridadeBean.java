package br.exemplo.gerenciadordetarefas.Controller;

import br.exemplo.gerenciadordetarefas.DAO.PrioridadeDAO;
import br.exemplo.gerenciadordetarefas.Model.Prioridade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.util.List;

@Data
@Named
@RequestScoped
public class PrioridadeBean {

    public List<Prioridade> prioridadesCombo() {
        PrioridadeDAO prioridadeDAO = new PrioridadeDAO();
        return prioridadeDAO.buscarTodos();
    }

}
