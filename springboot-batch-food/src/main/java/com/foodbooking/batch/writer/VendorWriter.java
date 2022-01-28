package com.foodbooking.batch.writer;

import com.foodbooking.entity.Vendor;
import com.foodbooking.repository.VendorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class VendorWriter implements ItemWriter<Vendor>{
	
	@Autowired
	private VendorRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Vendor> list) throws Exception {
		repo.saveAll(list);
	}
}
