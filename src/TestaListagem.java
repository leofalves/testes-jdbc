import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.retornaCon();		
		
		PreparedStatement stm = con.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		stm.execute();
		ResultSet rst = stm.getResultSet();
		
		while(rst.next()) {
			Integer id = rst.getInt("ID");
			String nome = rst.getString("NOME");
			String descricao = rst.getString("DESCRICAO");
			
			System.out.println("ID: " + id);
			System.out.println("NOME: " + nome);
			System.out.println("DESRICAO: " + descricao);					
		}
		
		System.out.println("Fechando conexão...");
		con.close();
		
	}
}
