package Entities;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {
	private List<Tarefas> listaDeTarefas;

    public GerenciadorDeTarefas() {
        listaDeTarefas = new ArrayList<>();
    }

    public void adicionarTarefa(String nome, String descricao) {
        Tarefas novaTarefa = new Tarefas(nome, descricao, false);
        listaDeTarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {
        System.out.println("----- Lista de Tarefas -----");
        for (Tarefas tarefa : listaDeTarefas) {
            System.out.println(tarefa.getNome());
        }
    }

    public void marcarTarefaConcluida(int indice) {
        if (indice >= 0 && indice < listaDeTarefas.size()) {
            Tarefas tarefaConcluida = listaDeTarefas.get(indice);
            tarefaConcluida.setConcluida(true);
            System.out.println("Tarefa marcada como concluída!");
            listaDeTarefas.remove(indice);
        } else {
            System.out.println("Índice inválido!");
        }
    }
}
