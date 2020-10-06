package servlet.Cart;

import beans.User;
import dao.CartDAO;
import dao.impl.CartDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/addintocart")
public class AddintoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartDAO dao = new CartDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
throws ServletException, IOException {
		//获取当前用户
		HttpSession session=req.getSession();
		User user = (User) session.getAttribute("user");

		int commodityID = Integer.parseInt(req.getParameter("commodityID"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		boolean flag = dao.addCart(user.getUserId(),commodityID,amount);

		JSONObject json = new JSONObject();  //创建Json对象
		json.put("flag", flag);
		resp.getWriter().write(json.toString());
	}

}
