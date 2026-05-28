package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import model.Usuario;
import view.TelaSalvar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	public TelaLogin() {

		JPanel panel = new JPanel();
		setTitle("Sistema Cadastro de produtos");
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("BEM VINDO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(176, 10, 132, 30);
		panel.add(lblNewLabel_1);

		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(194, 68, 54, 15);
		panel.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(200, 127, 48, 12);
		panel.add(lblSenha);

		textField = new JTextField();
		textField.setBounds(99, 93, 279, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnLogin = new JButton("ENTRAR");
		btnLogin.setBounds(187, 220, 84, 33);
		panel.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(99, 154, 279, 21);
		panel.add(passwordField);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() || passwordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					return;
				}

				Usuario usuario = new Usuario();

				usuario.setUsuario(textField.getText());
				String senhaDigitada = new String(passwordField.getPassword());
				usuario.setSenha(senhaDigitada);

				UsuarioDAO dao = new UsuarioDAO();

				if (dao.validarLogin(usuario)) {
					dispose();
					TelaListar listar = new TelaListar();
					listar.setLocationRelativeTo(null);
					listar.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario ou Senha invalidos");
				}

			}
		});

	}
}
