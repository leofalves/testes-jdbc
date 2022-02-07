import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComPreparedStm {

	public static void main(String[] args) throws SQLException {
		
		//Com o PreparedStatement
		String nome = "FORNO'S";
		String descricao = "MICROONDAS";
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.retornaCon();		
		PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)"
				, Statement.RETURN_GENERATED_KEYS);		
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();
		
		
		ResultSet rst = stm.getGeneratedKeys(); // Retorna os IDs incluídos
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
		
		System.out.println("Fechando conexão...");
		con.close();
		
	}
}
