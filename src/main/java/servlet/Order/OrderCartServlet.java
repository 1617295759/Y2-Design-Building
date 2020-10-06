package servlet.Order;

import beans.Cart;
import beans.Order;
import beans.Sorting;
import beans.User;
import com.alibaba.fastjson.JSONObject;
import dao.CartDAO;
import dao.OrderDAO;
import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cartToorder")
public class OrderCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        HttpSession session=req.getSession();
        User user = (User) session.getAttribute("user");
        CartDAO cartdao =  new CartDAOImpl();
        OrderDAO orderdao =  new OrderDAOImpl();

        String recAdd = req.getParameter("recAdd");
        //从前端合并成集合，通过json传输，后端还原为集合
        String CartList = req.getParameter("cartlist");
        List<Cart> cartList = JSONObject.parseArray(CartList, Cart.class);
        List<Sorting> sortings = null;
        double sumprice = 0;
        //从购物车中移除
        for(Cart cart: cartList){
            cartdao.deleteCart(cart.getCartId());
            sortings.add(new Sorting(cart));
            sumprice += cart.getPrice();
        }
        Order order = new Order(sumprice,recAdd,user.getUserId());
        boolean flag = orderdao.addOrder(order,sortings);

        JSONObject json = new JSONObject();  //创建Json对象
        json.put("flag", flag);
        resp.getWriter().write(json.toString());
    }
}
