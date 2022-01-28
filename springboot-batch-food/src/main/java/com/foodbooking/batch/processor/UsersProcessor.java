package com.foodbooking.batch.processor;

import com.foodbooking.entity.Users;
import com.foodbooking.repository.UsersRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UsersProcessor implements ItemProcessor<Users, Users> {

	@Autowired
	private UsersRepository userRepo;

	@Override
	public Users process(Users user) throws Exception {
		Optional<Users> userFromDb = userRepo.findById(user.getUserId());
		if(userFromDb.isPresent()) {
//			user.setAccount(user.getAccount().add(userFromDb.get().getAccount()));
		}
		return user;
	}

}
