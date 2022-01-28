package com.foodbooking.batch.processor;

import com.foodbooking.entity.Vendor;
import com.foodbooking.repository.VendorRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class VendorsProcessor implements ItemProcessor<Vendor, Vendor> {

	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public Vendor process(Vendor vendor) throws Exception {
		Optional<Vendor> vendorDb = vendorRepo.findById(vendor.getId());
		if(vendorDb.isPresent()) {
			return vendorDb.get();
		}
		return vendor;
	}

}
