package th.co.ananta.x.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import th.co.ananta.x.web.base.XCache;

@Configuration
public class XServletContextInitializer implements ServletContextInitializer {

	private static final Logger log = LoggerFactory.getLogger(ServletContextInitializer.class);

	private static BasicDataSource ds;
	private static final String DB_URL = "DB_URL";
	private static final String DB_USER = "DB_USER";
	private static final String DB_PASS = "DB_PASS";

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		String dbUrl = System.getenv(DB_URL);
		String dbUser = System.getenv(DB_USER);
		String dbPass = System.getenv(DB_PASS);
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(dbUrl != null ? dbUrl : "jdbc:mysql://localhost:3306/ananta");
		ds.setUsername(dbUser != null ? dbUser : "root");
		ds.setPassword(dbPass != null ? dbPass : "abc123");
		ds.setMinIdle(5);
		ds.setMaxActive(100);
		ds.setMaxWait(10000);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);

		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}
			}
		}
		load(context);
	}

	private void load(ServletContext ctx) {
		ctx.setAttribute("c_statusDescription", XCache.getStatusDescription());
		ctx.setAttribute("c_branchDescription", XCache.getBranchDescription());
		ctx.setAttribute("c_orderStatusDescription", XCache.getOrderStatusDescription());
		ctx.setAttribute("c_emailTemplate", XCache.getEmailTemplate());
	}


	public static Connection getConnection() throws SQLException {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw e;
		}
	}
}

