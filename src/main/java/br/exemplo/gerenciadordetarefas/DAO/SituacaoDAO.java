package br.exemplo.gerenciadordetarefas.DAO;

import br.exemplo.gerenciadordetarefas.Model.Situacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class SituacaoDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Situacao> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Situacao> query = em.createQuery("SELECT s FROM Situacao s ", Situacao.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}