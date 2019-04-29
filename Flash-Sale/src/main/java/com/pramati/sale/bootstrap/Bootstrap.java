package com.pramati.sale.bootstrap;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.pramati.sale.model.Address;
import com.pramati.sale.model.Authority;
import com.pramati.sale.model.Availability;
import com.pramati.sale.model.Company;
import com.pramati.sale.model.Gender;
import com.pramati.sale.model.Orders;
import com.pramati.sale.model.Product;
import com.pramati.sale.model.Users;
import com.pramati.sale.repository.AddressRepository;
import com.pramati.sale.repository.AuthorityRepositories;
import com.pramati.sale.repository.CompanyRepository;
import com.pramati.sale.repository.OrderRepository;
import com.pramati.sale.repository.ProductRepository;
import com.pramati.sale.repository.UserRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private UserRepository userRepository;
	private AddressRepository addressRepository;
	private CompanyRepository companyRepository;
	private ProductRepository productRepository;
	private OrderRepository orderRepository;
	private AuthorityRepositories authorityRepositories;
		
	public Bootstrap(UserRepository userRepository, AddressRepository addressRepository,
			CompanyRepository companyRepository, ProductRepository productRepository, OrderRepository orderRepository, AuthorityRepositories authorityRepositories) {
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.companyRepository = companyRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		this.authorityRepositories = authorityRepositories;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
				init();
	}

	public void init() {
		// for initializing some data
		// To store users and their data
		Address address = new Address("Mid Town", "Road Number 1, Banjara Hills", "Hyderabad", "Telangana", "India", 500031);
		addressRepository.save(address);
		
		
		Authority roles = new Authority("sudhir", "ROLE_USER");
		Authority roles2 = new Authority("gautham", "ROLE_ADMIN");
		Set<Authority> rolesSet = new HashSet<>();
		rolesSet.add(roles);
		rolesSet.add(roles2);
		authorityRepositories.save(roles);
		authorityRepositories.save(roles2);
		
		Users users = new Users("sudhir","sudhir","kumar",new BigInteger("7993610710"),"Male","{noop}123","sudhir@gmail.com",true);
		users.getAddress().add(address);
		userRepository.save(users);
		
		
		
		Address address2 = new Address("Raheja Mindspace", "Titus building, Hitech City", "Hyderabad", "Telangana", "India", 500081);
		addressRepository.save(address2);
		Users users2 = new Users("gautham","Gautham","Jain",new BigInteger("1234567890"),"Male","{noop}1234","gautham@gmail.com",true);
		users2.getAddress().add(address2);
		userRepository.save(users2);
		
		
		// To keep some product data
		Company company = new Company("Fossil India", "29-ABCDE1234F-2-Z-5");
		companyRepository.save(company);
		Product product = new Product("Fossil SmartWatch", 12999.00, 30.00, 9099.30, company, "FS4656", "Leather", "Quartz Chronograph", Gender.Male, Availability.available);
		productRepository.save(product);
		
		
		// another product
		Company company2 = new Company("Rolex India", "18-ABCDE1234G-1-G-7");
		companyRepository.save(company2);
		Product product2 = new Product("Rolex Oyster", 803800.00, 10.00, 723420.00, company2, "Oyster, 41 mm, Oystersteel and yellow gold", "Yellow Rolesor", "Hybrid", Gender.Female, Availability.available);
		productRepository.save(product2);
		
		// some existing order
		/*
		 * Orders order = new Orders(); HashSet<Product> productSet = new HashSet<>();
		 * productSet.add(product); order.setProductSet(productSet);
		 * order.setUsers(users); orderRepository.save(order);
		 */
	}
}
