package ImpDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import IDao.IProductDao;
import beans.Category;
import beans.Product;
import db.IDataBase;
import db.DataBase;

public class ProductDao implements IProductDao{

	private IDataBase dataBase;

	public ProductDao() {

	}
	
	public void setDataBase(IDataBase dataBase) {
		this.dataBase = dataBase;
	}
	
	@Override
	public void add(Product p) {
		if(p.getCategory() != null) {
			try {
				PreparedStatement ps = dataBase.getConnection().prepareStatement("INSERT INTO products SET name = ?, description =?, image =?, regularPrice=?, discount=?, categorieId=?");
				ps.setString(1, p.getName());
				ps.setString(2, p.getDescription());
				ps.setString(3, p.getImage());
				ps.setDouble(4, p.getRegularPrice());
				ps.setDouble(5, p.getDiscount());
				ps.setInt(6, p.getCategory().getId());
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Product p) {
		if(p.getCategory() != null) {
			try {
				PreparedStatement ps = dataBase.getConnection().prepareStatement("UPDATE products SET name = ?, description =?, image =?, regularPrice=?, discount=?, categorieId=? WHERE id = ?");
				ps.setString(1, p.getName());
				ps.setString(2, p.getDescription());
				ps.setString(3, p.getImage());
				ps.setDouble(4, p.getRegularPrice());
				ps.setDouble(5, p.getDiscount());
				ps.setInt(6, p.getCategory().getId());
				ps.setInt(7, p.getId());
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void remove(Product p) {
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("DELETE FROM products WHERE id = ?");
			ps.setInt(1, p.getId());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product find(int id) {
		Product p = null;
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("SELECT * FROM products WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setImage(rs.getString("image"));
				p.setRegularPrice(rs.getDouble("regularPrice"));
				p.setDiscount(rs.getDouble("discount"));
				if(rs.getInt("categorieId") != 0) {
					PreparedStatement ps2 = dataBase.getConnection().prepareStatement("SELECT * FROM categories WHERE id =?");
					ps2.setInt(1, rs.getInt("categorieId"));
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next()) {
						Category c = new Category();
						c.setId(rs2.getInt("id"));
						c.setName(rs2.getString("name"));
						p.setCategory(c);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = null;
		try {
			PreparedStatement ps = dataBase.getConnection().prepareStatement("SELECT * FROM products");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				products = new ArrayList<>();
				rs.beforeFirst();
				while(rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setDescription(rs.getString("description"));
					p.setImage(rs.getString("image"));
					p.setRegularPrice(rs.getDouble("regularPrice"));
					p.setDiscount(rs.getDouble("discount"));
					if(rs.getInt("categorieId") != 0) {
						PreparedStatement ps2 = dataBase.getConnection().prepareStatement("SELECT * FROM categories WHERE id =?");
						ps2.setInt(1, rs.getInt("categorieId"));
						ResultSet rs2 = ps2.executeQuery();
						if(rs2.next()) {
							Category c = new Category();
							c.setId(rs2.getInt("id"));
							c.setName(rs2.getString("name"));
							p.setCategory(c);
						}
					}
					products.add(p);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}

}
