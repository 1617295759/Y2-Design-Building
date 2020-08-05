package database;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	static DataSource dSource = null;
    static Properties properties = new Properties();

    // 静态代码块初始化加载驱动
    static {
        // 通过类加载器来获得流
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            // 通过流读取配置文件中的内容到集合中
            properties.load(is);
            // 通过Druid工厂加载文件注册驱动,初始化池子
            dSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement stat,Connection conn) {
    	if(stat != null) {
    		try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(conn != null) {
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static void close(PreparedStatement stat,Connection conn,ResultSet rs) {
    	close(stat,conn);
    	if(rs != null) {
    		try {
    			rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static Connection getConnection() throws SQLException {
    	return dSource.getConnection();
    }
    
    public static DataSource getDSource() {
		return dSource;
	}
    
    public static JdbcTemplate getTemplate() {
    	return new JdbcTemplate(getDSource());
    }
}
