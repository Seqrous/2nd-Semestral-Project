package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Controllers.DataAccessException;


public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "companyDB";
	private static final String serverAddress = "localhost";

	private DBConnection() throws DataAccessException {

		String connectionString = String.format("jdbc:sqlserver://%s; databaseName=%s; integratedSecurity=True",
				serverAddress, dbName);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
			System.out.println("Success");
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("Missing JDBC driver", e);
			// System.err.println("Could not load JDBC driver");
			// e.printStackTrace();

		} catch (SQLException e) {
			//throw new DataAccessException(String.format("Could not connect to database %s@%s:", dbName,
				//	serverAddress), e);
			// System.out.println("Connection string was: " + connectionString.substring(0,
			// connectionString.length() - password.length()) + "....");
			e.printStackTrace();
		}
	}

	public static synchronized DBConnection getInstance() throws DataAccessException {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public void startTransaction() throws DataAccessException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new DataAccessException("Could not start transaction.", e);
		}
	}

	public void commitTransaction() throws DataAccessException {
		try {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not commit transaction", e);
		}
	}

	public void rollbackTransaction() throws DataAccessException {
		try {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw e;
				// e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not rollback transaction", e);
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
