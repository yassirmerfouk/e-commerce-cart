package ImpDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import IDao.ICategoryDao;
import beans.Category;
import beans.Product;
import db.IDataBase;
import db.DataBase;

public class CategoryDao implements ICategoryDao{

	private IDataBase dataBase;
	
	public CategoryDao() {
		
	}

	public void setDataBase(IDataBase dataBase) {
		this.dataBase = dataBase;
	}
	
	@Override
	public void add(Category c) {
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("INSERT INTO categories SET NAME = ?");
			ps.setString(1, c.getName());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void remove(Category c) {
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("DELETE FROM categories WHERE id = ?");
			ps.setInt(1, c.getId());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category find(int id) {
		Category c = null;
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("SELECT * FROM categories Where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				PreparedStatement ps2 = dataBase.getConnection().prepareStatement("SELECT * FROM products WHERE categorieId = ?");
				ps2.setInt(1, c.getId());
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					List<Product> products = new ArrayList<>();
					rs2.beforeFirst();
					while(rs2.next()) {
						Product p = new Product();
						p.setId(rs2.getInt("id"));
						p.setName(rs2.getString("name"));
						p.setDescription(rs2.getString("description"));
						p.setImage(rs2.getString("image"));
						p.setRegularPrice(rs2.getDouble("regularPrice"));
						p.setDiscount(rs2.getDouble("discount"));
						p.setCategory(c);
						products.add(p);
					}
					c.setProducts(products);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = null;
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("SELECT * FROM categories");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				categories = new ArrayList<>();
				rs.beforeFirst();
				while(rs.next()) {
					Category c = new Category();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					PreparedStatement ps2 = dataBase.getConnection().prepareStatement("SELECT * FROM products WHERE categorieId = ?");
					ps2.setInt(1, c.getId());
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next()) {
						List<Product> products = new ArrayList<>();
						rs2.beforeFirst();
						while(rs2.next()) {
							Product p = new Product();
							p.setId(rs2.getInt("id"));
							p.setName(rs2.getString("name"));
							p.setDescription(rs2.getString("description"));
							p.setImage(rs2.getString("image"));
							p.setRegularPrice(rs2.getDouble("regularPrice"));
							p.setDiscount(rs2.getDouble("discount"));
							p.setCategory(c);
							products.add(p);
						}
						c.setProducts(products);
					}
					categories.add(c);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

}
