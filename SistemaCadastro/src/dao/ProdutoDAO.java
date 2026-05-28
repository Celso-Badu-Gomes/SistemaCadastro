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
			System.out.println("Produto salvo com sucesso!");

		} catch (Exception e) {
			System.err.println("Erro ao salvar produto: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Produto> pesquisarProduto(String nome) {
		String sql = "SELECT * FROM produto WHERE nome LIKE ?";
		List<Produto> lista = new ArrayList<Produto>();

		try (Connection con = ConnectionFactory.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "%" + nome + "%");

			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					Produto p = new Produto();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setPreco(rs.getDouble("preco"));
					p.setQuantidade(rs.getInt("quantidade"));
					p.setDescricao(rs.getString("descricao"));
					lista.add(p);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro na busca: " + e.getMessage());
		}
		return lista;
	}

	public void excluirProduto(int id) {
		String sql = "DELETE FROM produto WHERE id = ?";

		try (Connection con = ConnectionFactory.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao excluir: " + e.getMessage());
		}
	}

	public void atualizarProduto(Produto produto) {
		String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";

		try (Connection con = ConnectionFactory.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setInt(5, produto.getId());

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao atualizar: " + e.getMessage());
		}
	}

	public Produto buscaPorID(int id) {
		String sql = "SELECT * FROM produto WHERE id = ?";

		try (Connection con = ConnectionFactory.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Produto p = new Produto();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setPreco(rs.getDouble("preco"));
					p.setQuantidade(rs.getInt("quantidade"));
					p.setDescricao(rs.getString("descricao"));

					return p;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
