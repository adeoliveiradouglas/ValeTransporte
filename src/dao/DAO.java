/*
 * Super classe com dados e m�todos usuados por todos os DAO.
 * Todo DAO deve ser uma extens�o dessa classe. 
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

public class DAO {
	@Getter
	private Connection dbConnection = null;
	@Getter @Setter
	private String sqlQuery = null; 
	@Getter
	protected String nomeTabela = null; 
	private String nomeBanco = "valetransporte";
	private String usuarioBanco = "valetransporte"; 
	private String senhaBanco = "suporte2017";
	private String ip = "localhost";
	@Getter @Setter
	private ResultSet resultado = null;
	@Getter @Setter
	private PreparedStatement statement = null;
	private boolean fecharConexao = true;
			
	protected final String cId;
			
	
	protected DAO (String tabelaDB, Connection conexao){
//		recebe a conexao de outra classe. � usada quando um DAO chama outro para evitar abertura de novas conex�es
		this.nomeTabela = tabelaDB;
		this.dbConnection = conexao;
		this.fecharConexao = false;
		this.cId = nomeTabela + ".id";
	}
	
	protected DAO (String tabelaDB){
		this.nomeTabela = tabelaDB;
		this.cId = nomeTabela + ".id";
	}

	protected void iniciaConexaoComBanco(String sql) {
		// inicia a conex�o com o banco de dados
		if (dbConnection == null)
//			s� inicia uma nova conex�o caso ela ainda n�o exista
			this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();
		
		try {
			statement = this.getDbConnection().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * protected void iniciaConexaoComBanco() { // inicia a conex�o com o banco de
	 * dados if (dbConnection == null) // s� inicia uma nova conex�o caso ela ainda
	 * n�o exista this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco,
	 * senhaBanco).getConnection(); }
	 */

	protected void encerraConexaocomBanco() {
		// fecha a conex�o com o banco
		try {
			if(this.statement != null)
				this.statement.close();
			
			if (fecharConexao){
				/*
				 * s� fecha a conex�o caso o DAO n�o seja dependente de outros
				 * exemplo: UsuarioDAO chama SetorDAO. SetorDAO n�o fecha a conexao, ent�o essa vari�vel de controle
				 * fica como falsa pois UsuarioDAO ainda precisa fazer novos acessos ao banco
				*/
				try {
					this.dbConnection.close();
					this.dbConnection = null;
				} catch (NullPointerException e) {}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected int getResultSize() {
//		retorna a quantidade de itens no resultSet ap�s uma consulta ao banco
		int item = 0;
		
		try {
			this.resultado.last();
			item = this.resultado.getRow();
			this.resultado.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return item;
	}
}
