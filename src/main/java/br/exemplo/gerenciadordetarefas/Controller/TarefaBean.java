package br.exemplo.gerenciadordetarefas.Controller;

import br.exemplo.gerenciadordetarefas.DAO.TarefaDAO;
import br.exemplo.gerenciadordetarefas.Model.Prioridade;
import br.exemplo.gerenciadordetarefas.Model.Responsavel;
import br.exemplo.gerenciadordetarefas.Model.Situacao;
import br.exemplo.gerenciadordetarefas.Model.Tarefa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Named
@ViewScoped
public class TarefaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer tarefaNumero;
    private String tarefaTitulo;
    private String tarefaDescricao;
    private String tarefaTituloDescricao;
    private Responsavel tarefaResponsavel;
    private Prioridade tarefaPrioridade;
    private Situacao tarefaSituacao;
    private Date tarefaData;

    private List<Tarefa> tarefasFiltradas;
    private Tarefa selecionarTarefa;

    @PostConstruct
    public void init() {
        tarefaNumero = null;
        tarefaTitulo = "";
        tarefaDescricao = "";
        tarefaTituloDescricao = "";
        tarefaResponsavel = new Responsavel();
        tarefaPrioridade = new Prioridade();
        tarefaSituacao = new Situacao();
        tarefaData = new Date();
        tarefasFiltradas = new ArrayList<>();
        selecionarTarefa = new Tarefa();
    }

    public String cadastrarTarefa() {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tarefaTitulo);
        novaTarefa.setDescricao(tarefaDescricao);
        novaTarefa.setResponsavel(tarefaResponsavel);
        novaTarefa.setPrioridade(tarefaPrioridade);
        novaTarefa.setDeadline(tarefaData);
        novaTarefa.setSituacao(Situacao.SITUACAO_INICIAL);

        TarefaDAO tarefaDAO = new TarefaDAO();
        tarefaDAO.salvar(novaTarefa);

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Tarefa cadastrada com sucesso!"));
        buscarTarefa();
        return null;
    }

    public void buscarTarefa() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        this.tarefasFiltradas = tarefaDAO.listarComFiltro(
                tarefaNumero,
                tarefaTituloDescricao,
                tarefaResponsavel,
                tarefaSituacao
        );

        if (tarefasFiltradas.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma tarefa encontrada.", null)
            );
        }
    }

    public void prepararEditar(Tarefa t) {
        this.selecionarTarefa = t;

     }

    public void excluir(Tarefa t) {
        this.selecionarTarefa = t;

    }

    public void confirmarExcluir() {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.remover(selecionarTarefa.getId());
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Tarefa excluída com sucesso."));
            buscarTarefa();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir.", e.getMessage()));
        }
    }

    public void concluir(Tarefa t) {
        try {
            this.selecionarTarefa = t;
            selecionarTarefa.setSituacao(Situacao.SITUACAO_CONCLUIDA);
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.atualizar(selecionarTarefa);
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Tarefa concluída com sucesso."));
            buscarTarefa();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao concluir.", e.getMessage()));
        }
    }

    // No seu TarefaBean
    public void atualizar() {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.atualizar(this.selecionarTarefa);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Tarefa atualizada com sucesso.")
            );
            buscarTarefa();   // recarrega a lista
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar.", e.getMessage())
            );
        }
    }

}

