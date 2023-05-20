package Entities;

public class Tarefas {
	private String nome;
    private String descricao;
    private boolean concluida;

    public Tarefas(String nome, String descricao, boolean concluida) {
        this.nome = nome;
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
