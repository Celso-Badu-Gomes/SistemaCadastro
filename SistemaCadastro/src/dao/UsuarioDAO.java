package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Usuario;

public class UsuarioDAO {
	public boolean validarLogin(Usuario usuario) {
		String sql = "SELECT * FROM usuario " + "WHERE usuario = ? " + "AND senha = ?";
		try {
			Connection con = ConnectionFactory.conectar();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
