package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProdutoDAO;
import model.Produto;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaSalvar extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel panel = new JPanel();
	private JTextField textNome;
	private JTextField textDescricao;
	private JTextField textPreco;
	private JTextField textQuantidade;
	private JLabel lblTitulo;
	private int idProduto = 0;

	public TelaSalvar() {
		setTitle("Cadastro de produtos");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 10, 576, 343);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 49, 44, 21);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Descrição:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 194, 66, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Preço:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 111, 44, 16);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quantidade:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(236, 114, 77, 11);
		panel.add(lblNewLabel_4);

		lblTitulo = new JLabel("CADASTRAR NOVO PRODUTO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitulo.setBounds(10, 10, 189, 20);
		panel.add(lblTitulo);

		textNome = new JTextField();
		textNome.setBounds(10, 68, 556, 24);
		panel.add(textNome);
		textNome.setColumns(10);

		textDescricao = new JTextField();
		textDescricao.setBounds(10, 209, 556, 40);
		panel.add(textDescricao);
		textDescricao.setColumns(10);

		textPreco = new JTextField();
		textPreco.setBounds(10, 137, 139, 24);
		panel.add(textPreco);
		textPreco.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setBounds(236, 137, 123, 23);
		panel.add(textQuantidade);
		textQuantidade.setColumns(10);

		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textNome.getText().isEmpty() || textPreco.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome e Preço são obrigatórios!");
					return;
				}
				try {
					Produto produto = new Produto();
					produto.setPreco(Double.parseDouble(textPreco.getText().replace(",", ".")));
					produto.setQuantidade(Integer.parseInt(textQuantidade.getText()));
					produto.setNome(textNome.getText());
					produto.setDescricao(textDescricao.getText());

					ProdutoDAO dao = new ProdutoDAO();

					if (idProduto == 0) {
						dao.salvarProdutos(produto);
						JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
						limparCampos();
					} else {
						produto.setId(idProduto);
						dao.atualizarProduto(produto);
						JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
					}
					dispose();
					TelaListar frame = new TelaListar();
					frame.setLocationRelativeTo(btnSalvar);
					frame.setVisible(true);

				} catch (NumberFormatException ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Erro: No campo Preço e Quantidade, digite apenas números.");
				}

			}

			private void limparCampos() {
				textNome.setText("");
				textDescricao.setText("");
				textPreco.setText("");
				textQuantidade.setText("");
				textNome.requestFocus(); // Coloca o cursor de volta no primeiro campo
			}
		});

		btnSalvar.setForeground(Color.GREEN);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(458, 292, 108, 41);
		panel.add(btnSalvar);

		JButton btnNewButton = new JButton("VOLTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaListar frame = new TelaListar();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(332, 292, 98, 41);
		panel.add(btnNewButton);

		setVisible(true);

	}

	public TelaSalvar(Produto produto) {
		this();
		this.idProduto = produto.getId();
		lblTitulo.setText("EDITAR PRODUTO ATUAL");
		textNome.setText(produto.getNome());
		textDescricao.setText(produto.getDescricao());
		textPreco.setText(String.valueOf(produto.getPreco()));
		textQuantidade.setText(String.valueOf(produto.getQuantidade()));
	}
}
