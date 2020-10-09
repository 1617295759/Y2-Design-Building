import dao.*;
import dao.impl.*;
import database.DBUtils;
import net.sf.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

public class test {


    public static void main(String[] args) {
        JdbcTemplate tem = DBUtils.getTemplate();
        CartDAO cartdao = new CartDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        SortingDAO sortdao = new SortingDAOImpl();
        UserDAO userdao = new UserDAOImpl();
        ProductDAO prodao = new ProductDAOImpl();

        //System.out.println(prodao.sortByAddedtimeAsc());
        //System.out.println( new Timestamp(System.currentTimeMillis()));
        JSONObject json = new JSONObject();  //创建Json对象
        int commodityID = 2;
        json.put("products", prodao.sortByAddedtimeAsc());
        System.out.println(json.toString());
    }
}
