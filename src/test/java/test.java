import dao.impl.ProductDAOImpl;
import database.DBUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class test {
    public static void main(String[] args) {
        JdbcTemplate template = DBUtils.getTemplate();
        List list = new ProductDAOImpl().sortByAddedtimeAsc();
        for (Object product:list) {
            System.out.println(product);
        }

    }
}
