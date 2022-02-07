import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComPreparedStm {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection con = connectionFactory.retornaCon();
		con.setAutoCommit(false);
		
		try {
			PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)"
					, Statement.RETURN_GENERATED_KEYS);					
			adicionarVariavel("Toca discos", "Vinil", stm);
			adicionarVariavel("Fonte", "Fonte de Notebook Dell", stm);			
			con.commit();
			stm.close();
			con.close();			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ROLLBACK EXECUTADO");
			con.rollback();
		}		
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
		//Testar se vai incluir o primeiro ao dar erro no segundo
		if(nome.equals("Fonte")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}
		
		stm.execute();
		ResultSet rst = stm.getGeneratedKeys(); // Retorna os IDs incluídos
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
	}
}
