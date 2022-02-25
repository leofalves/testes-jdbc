package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;

public class CategoriaDAO {
	
	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
					categorias.add(categoria);
				}				
			}
		}
		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C"
					+ " INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID"
				    + " ORDER BY C.NOME";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					// Só instancia uma nova categoria diferente da anteriore
					if(ultima==null || !ultima.getNome().equals(rs.getNString(2))) {
						Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
						ultima = categoria;
						categorias.add(categoria);
					
					}
					Produto produto = new Produto(rs.getInt(3), rs.getString(4), rs.getString(4));
					ultima.adicionar(produto);
				}				
			}
		}
		return categorias;	
	}
}
