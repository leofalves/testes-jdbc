import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Produto;

public class TestaInsertComProduto {

	public static void main(String[] args) throws SQLException {
		Produto comoda = new Produto("Cômoda", "Cômoda com gavetas");
		
		try(Connection connection = new ConnectionFactory().retornaCon()) {
			
			String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
			try(PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				stm.setString(1, comoda.getNome());
				stm.setString(2, comoda.getDescricao());
				
				stm.execute();
				
				try(ResultSet rst = stm.getGeneratedKeys()) {
					while(rst.next()) {
						comoda.setId(rst.getInt(1));
					}
				}
			}
		}
		System.out.println(comoda);
	}

}
