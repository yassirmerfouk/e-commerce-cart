package beans;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<CartItem> items;
	
	public Cart() {
		items = new ArrayList<>();
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public void add(CartItem c) {
		items.add(c);
	}
	
	public void clear() {
		items.clear();
	}
	
	public int getQte() {
		return items.size();
	}

	public boolean productExist(int id) {
		for(CartItem item : items) {
			if(item.getProduct().getId() == id)
				return true;
		}
		return false;
	}
	
	public void removeItem(int id) {
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getProduct().getId() == id) {
				items.remove(i);
				break;
			}
		}
	}
	
	public CartItem getItem(int id) {
		for(CartItem item : items) {
			if(item.getProduct().getId() == id)
				return item;
		}
		return null;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	public double calculateTotal() {
		double total = 0;
		for(CartItem item : items)
			total += item.calculatePrice();
		return total;
	}
	
}
