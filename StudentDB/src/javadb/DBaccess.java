package javadb;

import java.sql.*;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
public class DBaccess {

	private Connection connection;
	private static final String JDBC_EXCEPTION = "JDBC Exception: ";
	private static final String SQL_EXCEPTION = "SQL Exception: ";

	public DBaccess() {
		super();
	}
	
	/**
	 * Open database connection
	 */
	public void openConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String dbName = "studentDB";
		String connectionURL = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false";
		String user = "root";
		String pw = "";
		try {
			System.out.print("Load driver... ");
			Class.forName(driver);				// Explicitly load the JDBC-driver.
			System.out.println("OK");

			System.out.print("Open connection " + connectionURL + "... ");
			connection = DriverManager.getConnection(connectionURL, user, pw);
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close database connection
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Builds a prepared Statement from the sql string. 
	 * A DAO should used this to fill the parameters.
	 * 
	 * @param query,
	 *            the SQl query
	 */
	public PreparedStatement getStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
		}
	
	/**
	 * Executes a prepared statement without result. Used for insert, update and delete statements.
	 * 
	 * @param ps,
	 *            the prepared statement filled by a DAO
	 */
	public void executeUpdatePreparedStatement(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
		}
	
	
	/**
	 * Executes a prepared statement with result. Used for select statements.
	 * 
	 * @param ps,
	 *            the prepared statement filled by a DAO
	 */
	public ResultSet executeSelectPreparedStatement(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
		}
	
	

	/**
	 * Executes a prepared statement with result to get a generated key.
	 * 
	 * @param query,
	 *            the SQL query
	 */
	public PreparedStatement getStatementWithKey(String sql) throws SQLException {
		return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
	
	public int executeInsertPreparedStatement(PreparedStatement ps) throws SQLException {
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys(); 
		int key = 0;
		while (rs.next()) {
			key = rs.getInt(1);
		}
		return key;
	}


}
