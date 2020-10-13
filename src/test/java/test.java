import dao.*;
import dao.impl.*;
import database.DBUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class test {


    public static void main(String[] args) {
        JdbcTemplate template = DBUtils.getTemplate();
        CartDAO cartdao = new CartDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        SortingDAO sortdao = new SortingDAOImpl();
        UserDAO userdao = new UserDAOImpl();
        ProductDAO prodao = new ProductDAOImpl();

        String user_schema = "iotbackstage2.user";
        String cart_schema = "iotbackstage2.cart";
        String dbname = "iotbackstage2";



    }
}
