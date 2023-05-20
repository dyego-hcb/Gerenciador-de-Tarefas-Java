package Application;

import java.util.Scanner;
import Entities.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;
		GerenciadorDeTarefas gerenciaTarefas = new GerenciadorDeTarefas();

		do {
			System.out.println("----- Menu -----");
			System.out.println("1. Adicionar tarefa");
			System.out.println("2. Listar tarefas");
			System.out.println("3. Marcar tarefa como concluída");
			System.out.println("0. Sair");

			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer do scanner

			switch (opcao) {
			case 1:
				// Adicionar tarefa
				System.out.print("Nome da tarefa: ");
				String nomeTarefa = scanner.nextLine();
				System.out.print("Descrição da tarefa: ");
				String descricaoTarefa = scanner.nextLine();
				gerenciaTarefas.adicionarTarefa(nomeTarefa, descricaoTarefa);
				break;
			case 2:
				// Listar tarefas
				gerenciaTarefas.listarTarefas();
				break;
			case 3:
				// Marcar tarefa como concluída
				System.out.print("Informe o índice da tarefa concluída: ");
				int indice = scanner.nextInt();
				gerenciaTarefas.marcarTarefaConcluida(indice);
				break;
			case 0:
				// Sair do programa
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida!");
			}
		} while (opcao != 0);

		scanner.close();
	}

}
