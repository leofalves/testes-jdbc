import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CategoriaDAO;
import model.Categoria;
import model.Produto;

public class TestaListagemCategorias {
	
	public static void main(String[] args) throws SQLException {
		
		try(Connection conn = new ConnectionFactory().retornaCon()){
			CategoriaDAO categoriaPersistencia = new CategoriaDAO(conn);
			
			
			// ANTI-PATTERN QUERY N + 1 
			// FAZ UMA QUERY PRINCIPAL E N QRYS PARA CADA CATEGORIA
//			List<Categoria> listaCategorias = categoriaPersistencia.listar();
//			listaCategorias.forEach(ct -> {
//				System.out.println(ct);
//				try {
//					for(Produto produto : new ProdutoDAO(conn).buscar(ct)) {
//						System.out.println(ct.getNome() + " - " + produto.getNome());
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
			
			List<Categoria> listaCategorias = categoriaPersistencia.listarComProdutos();
			listaCategorias.forEach(ct -> {
				System.out.println(ct.getNome());
				for(Produto produto : ct.getProdutos()) {
					System.out.println(ct.getNome() + " - " + produto.getNome());
				}
			});
		}
	}

}
