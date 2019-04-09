package web.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TurnoDAO;
import dao.UsuarioDAO;
import entity.Turno;
import entity.Usuario;
import utilidades.Cripto;
import web.Logica;

@WebServlet("/login")
public class Login extends Logica{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		String email = pedido.getParameter("email");

		/*
		 * Busca usuario no banco: Caso tenha usado usuário administrador geral,
		 * vai gerar uma exception de Null e esse usuário fica como nulo. É
		 * exatamente isso que deve acontecer pois o administrador geral não
		 * está cadastrado no banco
		 */
		Usuario u = new UsuarioDAO().getByEmail(email);

		// criptografa senha digitada. A comparação é feita com as senhas criptografadas
		String senha = new Cripto().criptografa(pedido.getParameter("senha"));

		System.out.println(senha + " " + u.getNome());
		if (u != null && senha.equals(u.getSenha())) {
			// Caso o login seja válido
		} else if (email.equals("valetransporte@ctb.ba.gov.br")
				&& senha.equals(/*"SZm6ez170MniprpMv9XhH5HVQ24JYbhs9Z9niOLSGH4="*/"")) {
			// Desse modo, sempre existirá um usuário administrador, não
						// importando o que há no banco de dados

			// Cria um objeto usuario para Administrador
			u = new Usuario(
				0,
				"Administrador", 
				email, 
				senha, 
				"Administrador"
			);
		} else {
			// Senha errada, usuário inexistente...
			pedido.getRequestDispatcher("/erro403").forward(pedido, resposta);
		}
		// Coloca os dados do usuário na sessão
		pedido.getSession().setAttribute("usuario", u);
		
		Turno t = new TurnoDAO().getUltimo();

		if(t.getTurno() == null) {
//			no caso de não haver turnos na tabela 
			t.setConcluido(true);
		}
		
		if(t.isConcluido()) {
//			caso o último turno já esteja concluído
			t.resetTurno();
		}
		
		pedido.getSession().setAttribute("turno", t);
//		Manda mostrar a tela principal
		redireciona("/telaprincipal", pedido, resposta);
	}
}