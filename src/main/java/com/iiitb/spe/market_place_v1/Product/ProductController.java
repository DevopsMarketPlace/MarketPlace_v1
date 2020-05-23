package com.iiitb.spe.market_place_v1.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.market_place_v1.Store.Store;





@RestController
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return productService.fetchAllProducts();
	}
	
	@GetMapping("/products/{pid}")
	public Product getproduct(@PathVariable int pid)
	{
		Product getProduct = productService.fetchProductById(pid);
		if(getProduct==null)
		{
//			 throw new NotFoundException("Provided product not found");
		}
		return getProduct;
	}
	
	@PostMapping("/product")
	public int createProduct(@RequestBody Product product)
	{
		Product newProduct=productService.createNewProduct(product);
		return newProduct.getPid();
	}
	
	@PutMapping("/product/{pid}")
	public Product updateProduct(@PathVariable int pid,@RequestBody Product product)
	{
		  Product getProduct=productService.fetchProductById(pid);
		  if(getProduct==null)
	      {
//	            throw new NotFoundException("Requested product not found");
	      }
		return productService.updateProduct(product,getProduct);
	}
	
	@DeleteMapping("/product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		 Product getProduct=productService.fetchProductById(pid);
	        if(getProduct==null)
	        {
//	            throw new NotFoundException("Provided product not found");
	        }
	        productService.removeProduct(getProduct);
	        return "Successfully deleted";
	}
	
	@GetMapping("/product/product/{pid}")
	public List<Store> getproducts(@PathVariable  int pid)
	{
		 Product response = productService.fetchProductById(pid);
	        if (response == null) {
//	            throw new NotFoundException("Provided Product not found");
	        }

	        Product result=productService.fetchStoreList(pid);
	        if(result==null)
	        {
//	            throw new NotFoundException("No products found for product" + response.getName());
	        }

	        return result.getProductStoreList().parallelStream().map(x->x.getStore()).collect(Collectors.toList());

	    }


	}
