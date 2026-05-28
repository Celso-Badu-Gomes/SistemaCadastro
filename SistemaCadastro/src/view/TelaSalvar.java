package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

	public static void main(String[] args) {
		TelaSalvar frame = new TelaSalvar();
	}
	/**
	 * Create the frame.
	 */
	public TelaSalvar() {
		
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
		lblNewLabel_1.setBounds(10, 128, 44, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 194, 66, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Preço:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(177, 76, 44, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantidade:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(346, 79, 77, 11);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CADASTRAR PRODUTOS");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(203, 28, 155, 20);
		panel.add(lblNewLabel_5);
		
		textNome = new JTextField();
		textNome.setBounds(10, 148, 556, 33);
		panel.add(textNome);
		textNome.setColumns(10);
		
		textDescricao = new JTextField();
		textDescricao.setBounds(10, 209, 556, 40);
		panel.add(textDescricao);
		textDescricao.setColumns(10);
		
		textPreco = new JTextField();
		textPreco.setBounds(177, 95, 139, 24);
		panel.add(textPreco);
		textPreco.setColumns(10);
		
		textQuantidade = new JTextField();
		textQuantidade.setBounds(344, 95, 123, 23);
		panel.add(textQuantidade);
		textQuantidade.setColumns(10);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setBackground(Color.GRAY);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto();
				
				produto.setPreco(Double.parseDouble(textPreco.getText()));
				produto.setQuantidade(Integer.parseInt(textQuantidade.getText()));
				produto.setNome(textNome.getText());
				produto.setDescricao(textDescricao.getText());
				
				ProdutoDAO dao = new ProdutoDAO();
				dao.salvarProdutos(produto);
				
			}
		});
		
		btnSalvar.setForeground(Color.GREEN);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setBounds(237, 292, 108, 41);
		panel.add(btnSalvar);
		
		setVisible(true);

	}

}
