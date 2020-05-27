package com.iiitb.spe.market_place_v1.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.Exceptions.NotFoundException;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomProductFormat;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iiitb.spe.market_place_v1.Store.Store;





@RestController
@CrossOrigin("*")
public class ProductController {
	
	Logger logger = LogManager.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		logger.info("All Products Fetched");
		return productService.fetchAllProducts();
	}
	
	@GetMapping("/products/{pid}")
	public Product getproduct(@PathVariable int pid)
	{
		Product getProduct = productService.fetchProductById(pid);
		if(getProduct==null)
		{
			logger.warn("Product Not Found Pid="+pid);
			 throw new NotFoundException("Provided product not found");
		}
		logger.info("Product Fetched pid="+pid);
		return getProduct;
	}
	
	@PostMapping("/product")
	public int createProduct(@RequestBody Product product)
	{
		Product newProduct=productService.createNewProduct(product);
		logger.info("New Product Created pid="+newProduct.getPid());
		return newProduct.getPid();
	}
	
	@PutMapping("/product/{pid}")
	public Product updateProduct(@PathVariable int pid,@RequestBody Product product)
	{
		  Product getProduct=productService.fetchProductById(pid);
		  if(getProduct==null)
	      {

				logger.warn("Product Not Found Pid="+pid);
	            throw new NotFoundException("Requested product not found");
	      }
		  logger.info("Product Updated pid="+pid);
		return productService.updateProduct(product,getProduct);
	}
	
	@DeleteMapping("/product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		 Product getProduct=productService.fetchProductById(pid);
	        if(getProduct==null)
	        {

				logger.warn("Product Not Found Pid="+pid);
	            throw new NotFoundException("Provided product not found");
	        }
	        productService.removeProduct(getProduct);
	        logger.info("Product Deleted");
	        return "Successfully deleted";
	}
	
	@GetMapping("/product/product/{pid}")
	public List<Store> getproducts(@PathVariable  int pid)
	{
		 Product response = productService.fetchProductById(pid);
	        if (response == null) {

				logger.warn("Product Not Found Pid="+pid);
	            throw new NotFoundException("Provided Product not found");
	        }

	        Product result=productService.fetchStoreList(pid);
	        if(result==null)
	        {
	        	logger.warn("Store Not Found");
	            throw new NotFoundException("No products found for product" + response.getProductname());
	        }
	        logger.info("Store List Corresponding to Product fetched");
	        return result.getProductStoreList().parallelStream().map(x->x.getStore()).collect(Collectors.toList());

	    }

	    //aayush--new api created

		@PostMapping("/product/store/{sid}")
		public void addProductsToStore(@PathVariable("sid") int sid, @RequestBody List<CustomProductFormat> products)
		{
			Store existingStore=productService.isStorePresent(sid);
			if(existingStore==null)
			{

				logger.warn("Store Not Found Pid="+sid);
				throw new NotFoundException("Store not found");
			}


			logger.info("Product Added to store Sid="+sid);
			productService.addProductstoStore(products,existingStore);
		}

		@PostMapping("/product/{sid}")
		public void addNewProductstoStore(@PathVariable("sid") int sid,@RequestBody List<CustomProductFormat> products)
		{
			Store existingStore=productService.isStorePresent(sid);
			if(existingStore==null)
			{

				logger.warn("Store Not Found Pid="+sid);
				throw new NotFoundException("Store not found");
			}
			logger.info("Added New Product to Store sid="+sid);
			productService.addNewProductstoStore(products,existingStore);
		}


	}
