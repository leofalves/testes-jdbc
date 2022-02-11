import java.sql.Connection;
import java.sql.SQLException;

import dao.ProdutoDAO;
import model.Produto;

public class TestaInsertComProduto {

	public static void main(String[] args) throws SQLException {
		Produto comoda = new Produto("Guitarra", "Guitarra Elétrica Fender");
		
		try(Connection connection = new ConnectionFactory().retornaCon()) {
			ProdutoDAO persistenciaProduto = new ProdutoDAO(connection);
			persistenciaProduto.salvar(comoda);
			//List<Produto> = persistenciaProduto.listar()
		}
		System.out.println(comoda);
	}
}
