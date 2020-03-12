package com.gutseeandpro.k1project.todo.ToDoDroid.repositories;

import com.gutseeandpro.k1project.todo.ToDoDroid.pojo.UserData;

import org.springframework.data.mongodb.repository.MongoRepository;

import lombok.NonNull;

public interface UserDataRepository extends MongoRepository<UserData, String> {

}
