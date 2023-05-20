package UserInterface;

import Entities.Tarefa;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefasGUI {
	private List<Tarefa> listaDeTarefas;
	private JTextArea areaTexto;

	public GerenciadorDeTarefasGUI() {
		listaDeTarefas = new ArrayList<>();
		criarGUI();
	}

	private void criarGUI() {
		JFrame frame = new JFrame("Gerenciador de Tarefas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		JPanel painelSuperior = new JPanel();
		JTextField campoNome = new JTextField(20);
		JTextField campoDescricao = new JTextField(20);
		JButton botaoAdicionar = new JButton("Adicionar Tarefa");

		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeTarefa = campoNome.getText();
				String descricaoTarefa = campoDescricao.getText();
				if (!nomeTarefa.isEmpty()) {
					Tarefa novaTarefa = new Tarefa(nomeTarefa, descricaoTarefa, false);
					listaDeTarefas.add(novaTarefa);
					atualizarAreaTexto();
					JOptionPane.showMessageDialog(frame, "Tarefa adicionada com sucesso!");
					campoNome.setText("");
					campoDescricao.setText("");
				} else {
					JOptionPane.showMessageDialog(frame, "Por favor, informe um nome para a tarefa.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelSuperior.add(campoNome);
		painelSuperior.add(campoDescricao);
		painelSuperior.add(botaoAdicionar);

		areaTexto = new JTextArea(15, 30);
		areaTexto.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(areaTexto);

		JPanel painelInferior = new JPanel();
		JButton botaoConcluir = new JButton("Concluir Tarefa");

		botaoConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int indiceTarefa = areaTexto.getLineOfOffset(areaTexto.getCaretPosition());
					if (indiceTarefa >= 0 && indiceTarefa < listaDeTarefas.size()) {
						Tarefa tarefaConcluida = listaDeTarefas.get(indiceTarefa);
						tarefaConcluida.setConcluida(true);
						atualizarAreaTexto();
						JOptionPane.showMessageDialog(frame, "Tarefa marcada como concluída!");
					}
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		});

		JButton botaoRemover = new JButton("Remover Tarefa");

		botaoRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int indiceTarefa = areaTexto.getLineOfOffset(areaTexto.getCaretPosition());
					if (indiceTarefa >= 0 && indiceTarefa < listaDeTarefas.size()) {
						Tarefa tarefaRemovida = listaDeTarefas.remove(indiceTarefa);
						atualizarAreaTexto();
						JOptionPane.showMessageDialog(frame, "Tarefa removida: " + tarefaRemovida.getNome());
					}
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		});

		areaTexto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						int indiceTarefa = areaTexto.getLineOfOffset(areaTexto.getCaretPosition());
						if (indiceTarefa >= 0 && indiceTarefa < listaDeTarefas.size()) {
							Tarefa tarefaSelecionada = listaDeTarefas.get(indiceTarefa);
							String[] opcoes = { "Remover", "Concluir" };
							int escolha = JOptionPane.showOptionDialog(frame, "O que deseja fazer com a tarefa?",
									"Ação da Tarefa", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									opcoes, opcoes[0]);

							if (escolha == 0) {
								listaDeTarefas.remove(indiceTarefa);
								atualizarAreaTexto();
								JOptionPane.showMessageDialog(frame, "Tarefa removida: " + tarefaSelecionada.getNome());
							} else if (escolha == 1) {
								tarefaSelecionada.setConcluida(true);
								atualizarAreaTexto();
								JOptionPane.showMessageDialog(frame, "Tarefa marcada como concluída!");
							}
						}
					} catch (BadLocationException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		painelInferior.add(botaoConcluir);
		painelInferior.add(botaoRemover);

		frame.add(painelSuperior, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(painelInferior, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private void atualizarAreaTexto() {
		areaTexto.setText("");
		for (Tarefa tarefa : listaDeTarefas) {
			String status = tarefa.isConcluida() ? "[Concluída]" : "[Pendente]";
			areaTexto.append(status + " " + tarefa.getNome() + "\n");
		}
	}
}
