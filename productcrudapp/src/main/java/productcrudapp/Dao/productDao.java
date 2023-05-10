package productcrudapp.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import productcrudapp.model.Product;

@Component
public class productDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	// Create a new product:
	
	@Transactional
	public void createProduct(Product product) {

		this.hibernateTemplate.saveOrUpdate(product);

	}

	// Get all product:

	public List<Product> getProducts() {

		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
		
	}
	
	//Delete single product:
	
	@Transactional
	public void deleteProduct(int pId) {
		
		Product p = this.hibernateTemplate.load(Product.class, pId);
 		this.hibernateTemplate.delete(p);
	}
	
	//Get a single Product
	
	public Product getProduct(int pid) {
		
		return this.hibernateTemplate.get(Product.class, pid); 
		
	}

}
