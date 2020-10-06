package servlet.Cart;

import dao.CartDAO;
import dao.impl.CartDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletecart")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		CartDAO dao =  new CartDAOImpl();
		int cartID = Integer.parseInt(req.getParameter("cartID"));
		boolean flag = dao.deleteCart(cartID);

		JSONObject json = new JSONObject();  //创建Json对象
		json.put("flag", flag);
		resp.getWriter().write(json.toString());
	}

}
