package servlet.User;


import beans.Cart;
import beans.Order;
import beans.User;
import dao.CartDAO;
import dao.OrderDAO;
import dao.UserDAO;
import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.UserDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="login",urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartDAO cartdao = new CartDAOImpl();
	OrderDAO orderdao = new OrderDAOImpl();
	UserDAO userdao = new UserDAOImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
	    this.doPost(req,res);
	 }

	public void doPost(HttpServletRequest req, HttpServletResponse res)
	    throws IOException, ServletException{
		//1.设置编码
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf-8");
		//2.获取请求参数  3.封装user对象
		User user = new User();
		user.setName(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		//4.调用UserDao方法判断信息是否匹配
		int flag = 0;
		flag = userdao.queryByUsername(user);
		if(flag == 1){
			//信息匹配将用户信息，用户的购物车信息，订单信息装到session中
			HttpSession session=req.getSession();
			ArrayList<Cart> carts = cartdao.sortCartByTime(user.getUserId());
			ArrayList<Order> orders = orderdao.sortOrderByTime(user.getUserId());
			session.setAttribute("carts", carts);
			session.setAttribute("orders", orders);
			session.setAttribute("user", userdao.getUser(user.getName()));
			//json返回值
		}
		JSONObject json = new JSONObject();  //创建Json对象
		//0-无账号，1-匹配，2-不匹配
		json.put("flag", flag);
		json.put("user", userdao.getUser(user.getName()));
		res.getWriter().write(json.toString());
		/*
		res.getWriter().write(String)和res.getWriter().print(json)有什么区别？
		将实体对象转换为JSON Object转换
		JSONObject responseJSONObject = JSONObject.fromObject(historyEvent);
		*/
	 }
}
	 