import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		//String nome = "FORNO";
		String nome = "FORNO'S"; // SQL INJECTION
		String descricao = "MICROONDAS";
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.retornaCon();		
		
		Statement stm = con.createStatement();
		stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('" + nome + "','" + descricao + "')"
				, Statement.RETURN_GENERATED_KEYS);
		ResultSet rst = stm.getGeneratedKeys(); // Retorna os IDs incluídos
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
		
		System.out.println("Fechando conexão...");
		con.close();
		
	}
}
