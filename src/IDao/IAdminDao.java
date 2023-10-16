package IDao;

import beans.Admin;
import db.IDataBase;
import db.DataBase;

public interface IAdminDao {

	public Admin findByLoginAndPassword(String login,String password);
}
