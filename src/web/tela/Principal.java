package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;
import web.Logica;

@WebServlet("/telaprincipal")
public class Principal extends Logica{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		pedido.setAttribute("supervisores", new UsuarioDAO().getAllSupervisor());
		
		if(u!=null) 
			redireciona("logado/index.jsp", pedido, resposta);
		else
			redireciona("erro402", pedido, resposta);
	}
}
