package ImpDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IDao.IAdminDao;
import beans.Admin;
import db.IDataBase;
import db.DataBase;

public class AdminDao implements IAdminDao {

	IDataBase dataBase;

	public AdminDao() {

	}

	public void setDataBase(IDataBase dateBase) {
		this.dataBase = dateBase;
	}

	@Override
	public Admin findByLoginAndPassword(String login, String password) {
		Admin admin = null;
		try {
			PreparedStatement ps = dataBase.getConnection()
					.prepareStatement("SELECT * FROM admin WHERE login = ? AND password = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setLogin(rs.getString("login"));
				admin.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

}
