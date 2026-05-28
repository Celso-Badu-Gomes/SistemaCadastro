package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Color;

public class TelaListar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		TelaListar frame = new TelaListar();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public TelaListar() {

		JPanel panel = new JPanel();
		setTitle("Listagem de produtos");
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1;
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(36, 10, 553, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Pesquisar Produto");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 10, 121, 12);
		panel_1.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.setBounds(422, 266, 121, 37);
		panel_1.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(125, 8, 418, 18);
		panel_1.add(textField);
		textField.setColumns(10);

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Nome");
		model.addColumn("Preço");
		model.addColumn("Quantidade");
		model.addColumn("Descrição");
		model.addColumn("Ação");

		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);

		scroll.setBounds(10, 59, 533, 194);

		panel_1.add(scroll);
		listarProdutos();

		setVisible(true);
	}

	public void listarProdutos() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		ProdutoDAO dao = new ProdutoDAO();

		List<Produto> lista = dao.listar();

		if (lista.isEmpty()) {
			model.addRow(new Object[] { "", "", "", "", "", "" });

			return;
		}
		for (Produto produto : lista) {

			model.addRow(new Object[] { produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidade(),
					produto.getDescricao(), "Editar/Excluir"

			});
			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
			table.setRowHeight(25);
		}

	}

}
