package IDao;

import java.util.List;

import beans.Category;

public interface ICategoryDao {

	public void add(Category c);
	public void remove(Category c);
	public Category find(int id);
	public List<Category> getAll();
}
