package com.foodbooking.batch.processor;

import com.foodbooking.entity.FoodItem;
import com.foodbooking.repository.FoodItemRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class FoodItemsProcessor implements ItemProcessor<FoodItem, FoodItem> {

	@Autowired
	private FoodItemRepository foodItemRepo;

	@Override
	public FoodItem process(FoodItem foodItem) throws Exception {
		Optional<FoodItem> foodItemDb = foodItemRepo.findById(foodItem.getId());
		if(foodItemDb.isPresent()) {
			return foodItemDb.get();
		}
		return foodItem;
	}

}
