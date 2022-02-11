package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Produto;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;		
	}
	
	public void salvar(Produto produto) throws SQLException {
		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
		try(PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			
			stm.execute();
			
			try(ResultSet rst = stm.getGeneratedKeys()) {
				while(rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}
	}
}