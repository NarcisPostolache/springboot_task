package com.spectre.app.entity;

import com.spectre.app.enums.Status;

import java.util.Date;

public class UserBuilder {

    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private short age;
    private String phoneNumber;
    private String email;
    private Status status;
    private Date registrationDate;

    public UserBuilder withUserId(String userId){
        this.userId = userId;
        return this;
    }

    public UserBuilder withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withGender(String gender){
        this.gender = gender;
        return this;
    }

    public UserBuilder withAge(short age){
        this.age = age;
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withStatus(Status status){
        this.status = status;
        return this;
    }

    public UserBuilder withRegistrationDate(Date registrationDate){
        this.registrationDate = registrationDate;
        return this;
    }

    public User build(){
        return new User(userId, firstName, lastName, gender, age, phoneNumber, email,  status, registrationDate);
    }

}
