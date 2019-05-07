package com.pramati.sale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramati.sale.entity.Address;
import com.pramati.sale.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public boolean processUserAddress(Set<Address> userAddresses) {
		List<Address> userAddressList = new ArrayList<>(userAddresses);		
		if(addressRepository.saveAll(userAddressList) != null)
			return true;
		return false;
	}
}
