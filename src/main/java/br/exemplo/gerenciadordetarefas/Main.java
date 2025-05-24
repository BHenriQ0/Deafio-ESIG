package br.exemplo.gerenciadordetarefas;

import br.exemplo.gerenciadordetarefas.DAO.JPAUtil;
import br.exemplo.gerenciadordetarefas.Model.Prioridade;
import br.exemplo.gerenciadordetarefas.Model.Responsavel;
import br.exemplo.gerenciadordetarefas.Model.Situacao;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Responsavel responsavel = new Responsavel();
            responsavel.setNome("Fabia Oliveira");
            em.persist(responsavel);

            Responsavel responsavel2 = new Responsavel();
            responsavel2.setNome("Bruno Henrique");
            em.persist(responsavel2);

            Responsavel responsavel3 = new Responsavel();
            responsavel3.setNome("Billy James");
            em.persist(responsavel3);

            Prioridade prioridadeAlta = new Prioridade();
            prioridadeAlta.setDescricao("Alta");
            em.persist(prioridadeAlta);

            Prioridade prioridadeMedia = new Prioridade();
            prioridadeMedia.setDescricao("Média");
            em.persist(prioridadeMedia);

            Prioridade prioridadeBaixa = new Prioridade();
            prioridadeBaixa.setDescricao("Baixa");
            em.persist(prioridadeBaixa);

            Situacao situacaoAndamento = new Situacao();
            situacaoAndamento.setDescricao("Em andamento");
            em.persist(situacaoAndamento);

            Situacao situacaoConcluida = new Situacao();
            situacaoConcluida.setDescricao("Concluída");
            em.persist(situacaoConcluida);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }

    }
}
