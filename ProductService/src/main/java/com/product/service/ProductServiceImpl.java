package com.product.service;
 

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	 
	@Override
	public ProductDto addProduct(ProductDto userDto) {
		Product newProduct = modelMapper.map(userDto, Product.class);
		try {
			Product productAdded = productRepository.save(newProduct);
			return modelMapper.map(productAdded, ProductDto.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Object getProduct(int id) {
		try {
			Optional<Product> existingProduct =  productRepository.findById(id); 
			if(existingProduct.isPresent()) { 
					return modelMapper.map(existingProduct.get(), ProductDto.class);					
				
			}
			throw new Exception("Product not found!!!"); 
			
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@Override
	public String deleteProduct(int id) {
		try {
			
			Optional<Product> checkDeletion = productRepository.findById(id);
			
			if(checkDeletion.isPresent()) {
				productRepository.deleteById(id);
				return "Product Record Deleted Successfully !!!";
			}else {
				throw new Exception("Product Not found!!");
			}
		} catch (Exception e) {
			e.getMessage();
			return "Product Not found!!!";
		}
	}

	
	
	@Override
	public Object updateProduct(int id, ProductDto newValues) {
		try {
			Optional<Product> updateObj = productRepository.findById(id);
			if(updateObj.isPresent()) {
				Product obj =  updateObj.get();
				
				Product updatedProduct = Product.builder()
						 	.pid(obj.getPid())
			                .productName(newValues.getProductName() != null ? newValues.getProductName() : obj.getProductName())
			                .productDesc(newValues.getProductDesc() != null ? newValues.getProductDesc() : obj.getProductDesc())
			                .price(newValues.getPrice() != 0 ? newValues.getPrice() : obj.getPrice())
			                .quantity(newValues.getQuantity() != 0 ? newValues.getQuantity() : obj.getQuantity())
			                .category(newValues.getCategory() != null ? newValues.getCategory() : obj.getCategory())
			                .inStock(newValues.isInStock() ? newValues.isInStock() : obj.isInStock())
			                .color(newValues.getColor() != null ? newValues.getColor() : obj.getColor())
			                .build();
				  
				
				
				Product savedProduct = productRepository.saveAndFlush(updatedProduct);
				return modelMapper.map(savedProduct, ProductDto.class);
			}else {
				throw new Exception("Product is not present");
			}
			
		} catch (Exception e) {
			e.getMessage(); 
			return "Product not found!!!";
		} 
	}

	@Override
	public boolean decreaseQuantityForProductBy(int id, int quantity) {
		try {
		    Optional<Product> existingProduct =	productRepository.findById(id);
			if(existingProduct.isPresent()) {
				Product obj = existingProduct.get();
				obj.setQuantity(obj.getQuantity() - quantity);
				
				productRepository.saveAndFlush(obj);
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		try {
			List<Product> allProducts = productRepository.findAll();
			
			return allProducts.stream()
	                .map(product -> modelMapper.map(product, ProductDto.class))
	                .collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error while fetching all products: {}", e.getMessage());
		}
		return null;
	}

}
