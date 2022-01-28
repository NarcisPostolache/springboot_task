package com.foodbooking.batch;

import com.foodbooking.batch.processor.FoodItemsProcessor;
import com.foodbooking.batch.processor.UsersProcessor;
import com.foodbooking.batch.processor.VendorsProcessor;
import com.foodbooking.batch.reader.FoodItemReader;
import com.foodbooking.batch.reader.UserReader;
import com.foodbooking.batch.reader.VendorReader;
import com.foodbooking.batch.writer.FoodItemWriter;
import com.foodbooking.batch.writer.UsersWriter;
import com.foodbooking.batch.writer.VendorWriter;
import com.foodbooking.entity.FoodItem;
import com.foodbooking.entity.Users;
import com.foodbooking.entity.Vendor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class FoodDataImportJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.user.file}")
	Resource userResource;

	@Value("${input.vendors.file}")
	Resource vendorsResource;

	@Value("${input.foodItems.file}")
	Resource foodItemsResource;
	
	@Autowired
	UsersProcessor usersProcessor;

	@Autowired
	FoodItemsProcessor foodItemsProcessor;

	@Autowired
	VendorsProcessor vendorsProcessor;

	@Autowired
	UsersWriter usersWriter;

	@Autowired
	FoodItemWriter foodItemWriter;

	@Autowired
	VendorWriter vendorWriter;
	
	@Bean(name = "usersJob")
	public Job usersJob() {
		Step step = stepBuilderFactory.get("step-1")
				.<Users, Users> chunk(2)
				.reader(new UserReader(userResource))
				.processor(usersProcessor)
				.writer(usersWriter)
				.build();

		Job job = jobBuilderFactory.get("users-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}

	@Bean(name = "vendorsJob")
	public Job vendorsJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<Vendor, Vendor> chunk(2)
				.reader(new VendorReader(vendorsResource))
				.processor(vendorsProcessor)
				.writer(vendorWriter)
				.build();

		Job job = jobBuilderFactory.get("vendors-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}

	@Bean(name = "foodItemsJob")
	public Job foodItemsJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<FoodItem, FoodItem> chunk(2)
				.reader(new FoodItemReader(foodItemsResource))
				.processor(foodItemsProcessor)
				.writer(foodItemWriter)
				.build();

		Job job = jobBuilderFactory.get("food-items-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
