package servlet;

import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.SortingDAO;
import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.SortingDAOImpl;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getInfo")
public class GetInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException{
        this.doPost(req,res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/json;charset=utf-8");
        JSONObject json = new JSONObject();  //创建Json对象

        String flag = req.getParameter("flag");
        switch(flag) {
            //返回购物车信息
            case "1": {
                CartDAO cartdao = new CartDAOImpl();
                int userID = Integer.parseInt(req.getParameter("userID"));
                json.put("carts", cartdao.sortCartByTime(userID));
                break;
            }
            //返回单个订单中各个条目信息
            case "2": {
                SortingDAO sortdao = new SortingDAOImpl();
                int orderID = Integer.parseInt(req.getParameter("orderID"));
                json.put("sorts", sortdao.getSorting(orderID));
                break;
            }
            //返回商品信息
            case "3": {
                ProductDAO productdao = new ProductDAOImpl();
                int commodityID = Integer.parseInt(req.getParameter("commodityID"));
                json.put("products", productdao.sortByPriceAsc());
                break;
            }
            //返回订单信息
            case "4": {
                OrderDAO orderdao = new OrderDAOImpl();
                int userID = Integer.parseInt(req.getParameter("userID"));
                json.put("orders", orderdao.sortOrderByTime(userID));
                break;
            }
        }
        res.getWriter().write(json.toString());
    }
}
