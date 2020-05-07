package com.iiitb.spe.market_place_v1.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiitb.spe.market_place_v1.Store.Store;



@RestController
public class ProductController {
	
	@Autowired
	ProductRepo repo;
	
	@GetMapping("/products")
	public List<Product> getProducts()
	{
		return repo.findAll();
	}
	
	@GetMapping("/products/{pid}")
	public Optional<Product> getproduct(@PathVariable int pid)
	{
		return repo.findById(pid);
	}
	
	@PostMapping("/product")
	public String setProduct(@RequestBody Product product)
	{
		repo.save(product);
		return "Saved Successfully";
	}
	
	@PutMapping("/product/{pid}")
	public String putProduct(@RequestBody Product product)
	{
		repo.save(product);
		return "Updated Successfully";
	}
	
	@DeleteMapping("/product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		repo.deleteById(pid);
		return "Deleted Successfully";
	}
	
	@GetMapping("/product/store/{pid}")
	public List<Store> getStores(@PathVariable  int pid)
	{
		return repo.getStoreList(pid).getProductStoreList().parallelStream().map(x-> x.getStore()).collect(Collectors.toList());
	}
	
}
