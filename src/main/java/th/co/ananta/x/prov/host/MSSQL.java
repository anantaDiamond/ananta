package th.co.ananta.x.prov.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import th.co.ananta.x.config.XServletContextInitializer;
import th.co.ananta.x.config.XServletContextInitializer_MSSQL;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MSSQL {

	private static final Logger log = LoggerFactory.getLogger(MSSQL.class);

	private Connection connection;
	private String sql;
	private PreparedStatement preparedStatement;

	public MSSQL(String sql) throws SQLException {
		init(sql);
	}

	private void init(String sql) throws SQLException {
		this.sql = sql;
		connection = XServletContextInitializer_MSSQL.getConnection();
		preparedStatement = connection.prepareStatement(sql);

	}

	public ResultSet executeQuery() throws SQLException {
		log.info(sql);
		return preparedStatement.executeQuery();
	}

	public int executeUpdate() throws SQLException {
		log.info(sql);
		return preparedStatement.executeUpdate();
	}

	public void setString(int index, String value) throws SQLException {
		preparedStatement.setString(index, value);
	}

	public void setObject(int index, Object value) throws SQLException {
		preparedStatement.setObject(index, value);
	}

	public void setBinaryStream(int index, InputStream inputStream) throws SQLException {
		preparedStatement.setBinaryStream(index, inputStream);
	}

	public void close() {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
}
