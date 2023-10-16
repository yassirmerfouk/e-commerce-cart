package db;

import java.sql.Connection;

public interface IDataBase {
	public void connect();
	public Connection getConnection();
}
