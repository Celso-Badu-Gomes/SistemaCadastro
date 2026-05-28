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

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
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
		textField.setBounds(32, 93, 378, 21);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(32, 149, 378, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnLogin = new JButton("ENTRAR");
		btnLogin.setBounds(176, 220, 84, 33);
		panel.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Usuario usuario = new Usuario();

				usuario.setUsuario(textField.getText());
				usuario.setSenha(textField_1.getText());

				UsuarioDAO dao = new UsuarioDAO();

				boolean usuarioValido = dao.validarLogin(usuario);
				
				if (usuarioValido) {
					//dispose(); new TelaSalvar();
					dispose(); new TelaListar();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario invalido");
				}

			}
		});

	}
}
