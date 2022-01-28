package com.foodbooking.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    @Qualifier("usersJob")
    Job usersJob;

    @Autowired
    @Qualifier("vendorsJob")
    Job vendorsJob;

    @Autowired
    @Qualifier("foodItemsJob")
    Job foodItemsJob;
    
    @GetMapping("/run-users-batch-job")
    public String handleUsersJob() throws Exception {

            JobParameters jobParameters = new JobParametersBuilder()
            								.addString("source", "Users")
            								.toJobParameters();
            jobLauncher.run(usersJob, jobParameters);

        return "Batch job has been invoked";
    }

    @GetMapping("/run-vendors-batch-job")
    public String handleVendorsJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("source", "Vendors")
                .toJobParameters();
        jobLauncher.run(vendorsJob, jobParameters);

        return "Batch job has been invoked";
    }

    @GetMapping("/run-food-items-batch-job")
    public String handleFoodItemsJob() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("source", "Food Items")
                .toJobParameters();
        jobLauncher.run(foodItemsJob, jobParameters);

        return "Batch job has been invoked";
    }
}