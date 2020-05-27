package com.iiitb.spe.market_place_v1.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iiitb.spe.market_place_v1.Store.Store;
import com.iiitb.spe.market_place_v1.Store.StoreService;
import com.iiitb.spe.market_place_v1.WrapperClasses.CustomProductFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepository;

	//--aayush
	@Autowired
	private StoreService storeService;

	//@Autowired ProductStoreReporitory productStoreReporitory;
	
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
	
	public Product updateProduct(Product upProduct)
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

	public Store isStorePresent(int id)
	{
		return storeService.fetchStoreById(id);
	}

	public void addProductstoStore(List<CustomProductFormat> products,Store store)
	{

		List<ProductStore> productStoreList=new ArrayList<ProductStore>();
		for(CustomProductFormat p:products)
		{
			Product product=new Product(p.getPid(),p.getPname(),p.getPprice());

			productStoreList.add(new ProductStore(product,store,p.getQuantity(),p.getDisprice()));
		}
		store.getProductStoreList().addAll(productStoreList);



		storeService.updateStore(store);

	}
	public void addNewProductstoStore(List<CustomProductFormat> products,Store store)
	{
		List<ProductStore> productStoreList=new ArrayList<ProductStore>();
		System.out.println(store.getSid());
//		Store s=new Store();
//		s.setName("mystore");
//		s.setDuration(30);
//		s.getAddress().setCity("durg");
//		s.getAddress().setLat(10);
//		s.getAddress().setLon(10);
//		s.getAddress().setPincode("491001");
		for(CustomProductFormat p:products)
		{
			Product product=new Product();
			product.setProductname(p.getPname());
			product.setPprice(p.getPprice());
			productRepository.save(product);
			ProductStore productStore=new ProductStore();
			productStore.setStore(store);
			System.out.println(productStore.getStore().getName());
			productStore.setProduct(product);
			productStore.setDispirce(p.getDisprice());
			productStore.setQuantity(p.getQuantity());
			productStoreList.add(productStore);
		}
		store.getProductStoreList().addAll(productStoreList);
		System.out.println("aayush");

		storeService.updateStore(store);


	}

	public void createNewProducts(List<Product> productList)
	{
		productRepository.saveAll(productList);
	}

}
