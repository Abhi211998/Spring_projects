package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.Dao.productDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private productDao productdao;
	
	@RequestMapping("/")
	public String home(Model m) {
		
		List<Product> allProducts = productdao.getProducts();
		
		m.addAttribute("products", allProducts);
		
		return "index";
	}
	
	//Adding a product-form:
	
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		
		m.addAttribute("title", "ADD PRODUCT");
		return "add_product_form";
	}
	
	//Handling adding a product:
	
	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request) {
		
		System.out.println(product);
		productdao.createProduct(product);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	
	//Handling deleting a product:
	
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteSingleProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		
		this.productdao.deleteProduct(productId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	
	//Updating a product-Form:
	
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int pId, Model m) {
		
		Product product = this.productdao.getProduct(pId);
		m.addAttribute("product", product);
		return "update_form";
	}
}
