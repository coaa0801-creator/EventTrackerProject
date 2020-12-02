package com.skilldistillery.cheyenne.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.cheyenne.entities.Address;
import com.skilldistillery.cheyenne.repositories.AddressRepository;
import com.skilldistillery.cheyenne.repositories.CustomerRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository repo;
	@Autowired
	private CustomerRepository cRepo;

	@Override
	public List<Address> findAllAddresss() {
		return repo.findAll();
		
	}

	@Override
	public Address findById(int id) {
		Optional<Address> addressOpt = repo.findById(id);
		Address address = null;
		if (addressOpt.isPresent()) {
			address = addressOpt.get();
		}
		return address;
	}

	@Override
	public Address createAddress(Address newAddress) {
//		if(newAddress.getCustomer() != null && !newAddress.getCustomer().getAddresses().contains(newAddress)) {
//			newAddress.getCustomer().addAddress(newAddress);
//			cRepo.save(newAddress.getCustomer());
//		}
		return repo.saveAndFlush(newAddress);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Address> addressOpt = repo.findById(id);
		if (addressOpt.isPresent()) {
			Address address = addressOpt.get();
//			address.getCustomer().removeAddress(address);
//			cRepo.saveAndFlush(address.getCustomer());
			repo.delete(address);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public Address updateAddress(Address newAddress, int id) {
		Optional<Address> addressOpt = repo.findById(id);
		Address update = null;
		if (addressOpt.isPresent()) {
			update = addressOpt.get();
			if (newAddress.getAddress() != null) {update.setAddress(newAddress.getAddress());	}
			if (newAddress.getAddress2() != null) {update.setAddress2(newAddress.getAddress2());	}
			if (newAddress.getCity() != null) {update.setCity(newAddress.getCity());	}
			if (newAddress.getState() != null) {update.setState(newAddress.getState());	}
			if (newAddress.getZip() != null) {update.setZip(newAddress.getZip());	}
			if (newAddress.getPhone() != null) {update.setPhone(newAddress.getPhone());	}
//			if (newAddress.getCustomer() != null) {cRepo.save(newAddress.getCustomer());    update.setCustomer(newAddress.getCustomer());	}
			
		}
		return repo.saveAndFlush(update);

	}
}
