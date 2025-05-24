package br.exemplo.gerenciadordetarefas.DAO;

import br.exemplo.gerenciadordetarefas.Model.Prioridade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class PrioridadeDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Prioridade> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Prioridade> query = em.createQuery("SELECT p FROM Prioridade p ", Prioridade.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
