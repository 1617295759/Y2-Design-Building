package servlet.Order;

import beans.Order;
import beans.Sorting;
import com.alibaba.fastjson.JSONArray;
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
import java.io.IOException;
import java.util.ArrayList;
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

        JSONObject json = new JSONObject();  //创建Json对象
        CartDAO cartdao = new CartDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();

        int userID = Integer.parseInt(req.getParameter("userid"));
        double sumprice = 0;
        sumprice = Integer.parseInt(req.getParameter("sumprice"));
        //收获地址
        String recAdd = req.getParameter("recadd");
        //从前端合并成集合，通过json传输，后端进行拆解分析
        String CartList = req.getParameter("cartlist");
        JSONArray cartList = JSONObject.parseArray(CartList);
        List<Sorting> sortings = new ArrayList<Sorting>();

        for (int i = 0; i < cartList.size(); i++) {
            JSONObject jo = cartList.getJSONObject(i);
            //Product pro = JSONObject.parseObject(jo.toString(), Product.class);

            cartdao.deleteCart((int) (jo.get("cartId")));
            sortings.add(new Sorting(
                    (int) (jo.get("amount")),
                    Double.valueOf((int) (jo.get("price"))),
                    (int) (jo.get("commodityId")),
                    jo.get("color").toString()
            ));
        }
        //添加订单数据
        Order order = new Order(sumprice, recAdd, userID);
        boolean flag = orderdao.addOrder(order, sortings);
        json.put("flag", flag);
        resp.getWriter().write(json.toString());
    }
}
