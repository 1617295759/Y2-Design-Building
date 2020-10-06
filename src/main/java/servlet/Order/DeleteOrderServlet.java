package servlet.Order;

import com.alibaba.fastjson.JSONObject;
import dao.OrderDAO;
import dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteorder")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDAO dao =  new OrderDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		int orderID = Integer.parseInt(req.getParameter("orderID"));
		boolean flag = dao.deleteOrder(orderID);
		JSONObject json = new JSONObject();  //创建Json对象
		json.put("flag", flag);
		resp.getWriter().write(json.toString());
	}

}
