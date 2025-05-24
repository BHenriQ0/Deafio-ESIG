package br.exemplo.gerenciadordetarefas.DAO;

import br.exemplo.gerenciadordetarefas.Model.Responsavel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class ReponsavelDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Responsavel> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Responsavel> query = em.createQuery("SELECT r FROM Responsavel r ", Responsavel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
