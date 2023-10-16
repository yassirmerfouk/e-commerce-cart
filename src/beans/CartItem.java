package beans;

public class CartItem {

	private Product product;
	private int qte;
	
	public CartItem() {
		super();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	
	public double calculatePrice() {
		return product.getSalePrice() * qte;
	}
	
	
}
