package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;

/**
 * Servlet implementation class indexservlet
 */
@WebServlet({"/indexservlet"})
public class indexservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public indexservlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		UserService us= new UserService();
	    User user = us.login(id, pass);		
		String msgid = null; 
		String msgps = null; 
		String msg = null; 
		
		if(null!=user) {
		session.setAttribute("User", user);
		session.setAttribute("Username", user.getName());
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);	
		}else if(null==us.login(id,pass)) {
			msg = "IDまたはパスワードが不正です";
			request.setAttribute("msg",msg);
		}	
		
		if(pass.equals("")) {
			msgps = "パスワードが未入力です";
			msg = null;
			request.setAttribute("msg",msg);
			request.setAttribute("msgps",msgps);
		}
		
		if(id.equals("")) {
			msgid = "idが未入力です";
			msg = null;
			request.setAttribute("msg",msg);
			request.setAttribute("msgid",msgid);
		}
			request.getRequestDispatcher("index.jsp").forward(request, response);		

	}

}
