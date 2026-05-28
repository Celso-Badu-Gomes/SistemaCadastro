package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProdutoDAO;
import model.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JLabel lblAvisoVazio;
	private JScrollPane scroll;

	public TelaListar() {
		setTitle("Listagem de produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 411);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 10, 621, 354);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Pesquisar Produto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 10, 121, 12);
		panel_1.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaSalvar frame = new TelaSalvar();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}

		});
		btnNewButton.setBounds(482, 296, 121, 37);
		panel_1.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(125, 8, 478, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		// CONFIGURAÇÃO DA TABELA
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Nome");
		model.addColumn("Preço");
		model.addColumn("Quantidade");
		model.addColumn("Descrição");
		model.addColumn("Ação");

		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 59, 593, 194);
		panel_1.add(scroll);

		// CONFIGURAÇÃO DO AVISO (Label)
		lblAvisoVazio = new JLabel("Nenhum produto cadastrado no momento.");
		lblAvisoVazio.setHorizontalAlignment(JLabel.CENTER);
		lblAvisoVazio.setForeground(Color.RED);
		lblAvisoVazio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAvisoVazio.setBounds(10, 59, 593, 194);
		lblAvisoVazio.setVisible(false);
		panel_1.add(lblAvisoVazio);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				{
					int linha = table.rowAtPoint(e.getPoint());
					int coluna = table.columnAtPoint(e.getPoint());

					if (linha < table.getRowCount() && linha >= 0 && coluna == 5) {
						int id = (int) table.getValueAt(linha, 0);

						Object[] opcoes = { "Editar", "Excluir", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null,
								"O que deseja fazer com o produto ID: " + id + "?", "Ação", JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

						if (escolha == 0) { // Clicou em Editar
							ProdutoDAO dao = new ProdutoDAO();
							Produto produto = dao.buscaPorID(id);
							dispose();
							new TelaSalvar(produto);

						} else if (escolha == 1) { // Clicou em Excluir
							confirmaDeletar(id);
						}
					}

				}
			}
		});

		textField.addKeyListener(new java.awt.event.KeyAdapter() {
			// busca instantania
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				String busca = textField.getText();
				pesquisarProdutos(busca);
			}
		});
		listarProdutos();
	}

	public void listarProdutos() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> lista = dao.listar();

		// LÓGICA DE EXIBIÇÃO
		if (lista == null || lista.isEmpty()) {
			scroll.setVisible(false); // Esconde a tabela
			lblAvisoVazio.setVisible(true); // Mostra o aviso
		} else {
			scroll.setVisible(true); // Mostra a tabela
			lblAvisoVazio.setVisible(false); // Esconde o aviso

			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
			table.setRowHeight(25);

			for (Produto produto : lista) {
				model.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getPreco(),
						produto.getQuantidade(), produto.getDescricao(), "Editar/Excluir" });
			}
		}
	}

	public void pesquisarProdutos(String busca) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		ProdutoDAO dao = new ProdutoDAO();

		List<Produto> lista;// = dao.pesquisarProduto(busca);

		if (busca.trim().isEmpty()) {
			lista = dao.listar();
		} else {
			lista = dao.pesquisarProduto(busca);
		}

		if (lista.isEmpty()) {
			scroll.setVisible(false);
			lblAvisoVazio.setText("Nenhum resultado para: '" + busca + "'");
			lblAvisoVazio.setVisible(true);
		} else {
			scroll.setVisible(true);
			lblAvisoVazio.setVisible(false);

			for (Produto p : lista) {
				model.addRow(new Object[] { p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getDescricao(),
						"Editar/Excluir" });
			}
			table.setRowHeight(25);
		}

	}

	private void confirmaDeletar(int id) {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir!", "Confirmar", JOptionPane.YES_OPTION);

		if (confirma == JOptionPane.YES_OPTION) {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluirProduto(id);

			listarProdutos();
			JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
		}
	}
}