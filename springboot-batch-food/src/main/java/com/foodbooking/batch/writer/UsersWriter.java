package com.foodbooking.batch.writer;

import com.foodbooking.entity.Users;
import com.foodbooking.repository.UsersRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class UsersWriter implements ItemWriter<Users>{
	
	@Autowired
	private UsersRepository repo;

	@Override
	@Transactional
	public void write(List<? extends Users> users) throws Exception {
		repo.saveAll(users);
	}
	
}
