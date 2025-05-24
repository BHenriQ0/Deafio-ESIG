package br.exemplo.gerenciadordetarefas.DAO;

import br.exemplo.gerenciadordetarefas.Model.Prioridade;
import br.exemplo.gerenciadordetarefas.Model.Responsavel;
import br.exemplo.gerenciadordetarefas.Model.Situacao;
import br.exemplo.gerenciadordetarefas.Model.Tarefa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public void salvar(Tarefa tarefa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarefa);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void atualizar(Tarefa tarefa) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarefa);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Tarefa buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Tarefa.class, id);
        } finally {
            em.close();
        }
    }

    public List<Tarefa> listarComFiltro(Integer numero,
                                        String tituloDescricao,
                                        Responsavel responsavel,
                                        Situacao situacao) {
        EntityManager em = JPAUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tarefa> cq = cb.createQuery(Tarefa.class);
        Root<Tarefa> root = cq.from(Tarefa.class);

        List<Predicate> predicates = new ArrayList<>();


        if (numero != null) {
            predicates.add(cb.equal(root.get("id"), numero));
        }


        if (tituloDescricao != null && !tituloDescricao.trim().isEmpty()) {
            String pattern = "%" + tituloDescricao.trim().toLowerCase() + "%";
            Predicate tituloLike = cb.like(cb.lower(root.get("titulo")), pattern);
            Predicate descricaoLike = cb.like(cb.lower(root.get("descricao")), pattern);
            predicates.add(cb.or(tituloLike, descricaoLike));
        }



        if (responsavel != null && responsavel.getId() != 0) {
            predicates.add(
                    cb.equal(
                            root.get("responsavel").get("id"),
                            responsavel.getId()
                    )
            );
        }


        if (situacao != null && situacao.getId() != 0) {
            predicates.add(
                    cb.equal(
                            root.get("situacao").get("id"),
                            situacao.getId()
                    )
            );
        }


        cq.where(predicates.toArray(new Predicate[0]));

        cq.orderBy(cb.asc(root.get("id")));

        TypedQuery<Tarefa> query = em.createQuery(cq);
        return query.getResultList();
    }


    public void remover(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Tarefa t = em.find(Tarefa.class, id);
            if (t != null) {
                em.remove(t);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
