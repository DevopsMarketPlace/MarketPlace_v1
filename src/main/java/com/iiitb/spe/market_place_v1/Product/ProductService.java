package com.iiitb.spe.market_place_v1.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepository;
	
	
	public List<Product> fetchAllProducts()
	{
		return productRepository.findAll();
	}
	
	
	public Product fetchProductById(int pid)
	{
		return productRepository.findById(pid).orElse(null);
	}
	
	public Product createNewProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product upProduct,Product existingproduct)
	{
		return productRepository.save(upProduct);
	}
	
	public void removeProduct(Product existingProduct)
	{
		productRepository.delete(existingProduct);
	}
	
	public Product fetchStoreList(int pid)
	{
		return productRepository.getStoreList(pid);
	}

}
