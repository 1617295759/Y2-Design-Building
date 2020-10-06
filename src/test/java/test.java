import dao.*;
import dao.impl.*;
import database.DBUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class test {


    public static void main(String[] args) {
        JdbcTemplate tem = DBUtils.getTemplate();
        CartDAO cartdao = new CartDAOImpl();
        OrderDAO orderdao = new OrderDAOImpl();
        SortingDAO sortdao = new SortingDAOImpl();
        UserDAO userdao = new UserDAOImpl();
        ProductDAO prodao = new ProductDAOImpl();

        System.out.println(prodao.sortByAddedtimeAsc());

    }
}
