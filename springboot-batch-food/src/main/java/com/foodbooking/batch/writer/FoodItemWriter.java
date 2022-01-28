package com.foodbooking.batch.writer;

import com.foodbooking.entity.FoodItem;
import com.foodbooking.repository.FoodItemRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class FoodItemWriter implements ItemWriter<FoodItem>{
	
	@Autowired
	private FoodItemRepository repo;

	@Override
	@Transactional
	public void write(List<? extends FoodItem> list) throws Exception {
		repo.saveAll(list);
	}
}
