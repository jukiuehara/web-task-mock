package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("loginId");
		String name = request.getParameter("userName");
		String price = request.getParameter("tel");
		String categoryid = request.getParameter("roleId");
		String des = request.getParameter("description");

		String msgid = "";
		String msgname = "";
		String msgprice = "";

		if (id.equals("")) {
			msgid = "商品IDは入力必須項目です";
			request.setAttribute("msgid", msgid);
		}
		if (name.equals("")) {
			msgname = "名前は入力必須項目です";
			request.setAttribute("msgname", msgname);
		}
		if (price.equals("")) {
			msgprice = "単価は入力必須項目です";
			request.setAttribute("msgprice", msgprice);
		}
		if (!(msgid.equals("")) || !(msgname.equals("")) || !(msgprice.equals(""))) {
			request.getRequestDispatcher("insert.jsp").forward(request, response);
		} else {

			try{
				int productId = Integer.parseInt(id);
				int tel = Integer.parseInt(price);
				int categorynumber = Integer.parseInt(categoryid);
				ProductService ps = new ProductService();
				String msg = "登録が完了しました";
				request.setAttribute("msg", msg);
				ps.insert(productId, categorynumber, name, tel, des);
				request.getRequestDispatcher("insert.jsp").forward(request, response);	
			} catch (Exception e) {
				String msg = "idが重複しました";
				request.setAttribute("msg", msg);
			    request.getRequestDispatcher("insert.jsp").forward(request, response);				
			}

		}

		doGet(request, response);
	}

}
