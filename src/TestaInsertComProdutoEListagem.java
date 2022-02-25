import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class TestaInsertComProdutoEListagem {

	public static void main(String[] args) throws SQLException {
		Produto comoda = new Produto("Viola", "Viola Gianini");
		
		try(Connection connection = new ConnectionFactory().retornaCon()) {
			ProdutoDAO persistenciaProduto = new ProdutoDAO(connection);
			persistenciaProduto.salvar(comoda);
			List<Produto> produtos = persistenciaProduto.listar();
			//produtos.stream().forEach(prod -> System.out.println(prod));
			produtos.forEach(System.out::println);
		}
	}
}
