import java.sql.SQLException;

public class TestaPoolConexoes {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		// Tenta abrir 20 conexões; Abre somente a quantidade definina no setMaxPoolSize;
		// Para verificar no MySQL, usar o comando: show processlist;
		for (int i=0; i <20; i++) {
			connectionFactory.retornaCon();
			System.out.println("Conexão número: " + i);
		}
	}
}
