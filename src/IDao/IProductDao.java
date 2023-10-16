package IDao;

import java.util.List;

import beans.Product;


public interface IProductDao {

	public void add(Product p);
	public void update(Product p);
	public void remove(Product p);
	public Product find(int id);
	public List<Product> getAll();
}
