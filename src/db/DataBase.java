package db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

public class DataBase implements IDataBase{

	private final static String HOST;
	private final static String DB_NAME;
	private final static String USER;
	private final static String PASSWORD;
	
	private Connection connection;
	
	static{
		HOST = "127.0.0.1";
		DB_NAME = "ecommerce_cart";
		USER = "root";
		PASSWORD = "";
	}
	
	public DataBase() {
		connect();
	}
	
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/"+DB_NAME,USER,PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
