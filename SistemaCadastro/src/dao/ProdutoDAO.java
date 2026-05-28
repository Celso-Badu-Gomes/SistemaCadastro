package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {

	public List<Produto> listar() {

		List<Produto> lista = new ArrayList<>();

		String sql = "SELECT * FROM produto ORDER BY id";

		try (Connection con = ConnectionFactory.conectar();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidade(rs.getInt("quantidade"));
				lista.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lista;
	}

	public void salvarProdutos(Produto produto) {
		String sql = "INSERT INTO produto (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?) ";

		try (Connection con = ConnectionFactory.conectar(); PreparedStatement stmt = con.prepareStatement(sql);) {

			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());

			stmt.executeUpdate();
			System.out.println("Produto aslvo com sucesso!");

		} catch (Exception e) {
			System.err.println("Erro ao salvar produto: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
