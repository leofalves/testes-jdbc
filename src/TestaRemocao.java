import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.retornaCon();		
		
		Statement stm = con.createStatement();
		stm.execute("DELETE FROM PRODUTO WHERE ID > 2");
		int linhasAfetadas = stm.getUpdateCount();
		
		System.out.println("Quantidade de linhas excluídas: " + linhasAfetadas);
		con.close();
		
	}
}
