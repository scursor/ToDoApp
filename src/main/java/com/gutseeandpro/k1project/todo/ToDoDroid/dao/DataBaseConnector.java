package com.gutseeandpro.k1project.todo.ToDoDroid.dao;

import com.gutseeandpro.k1project.todo.ToDoDroid.pojo.UserData;
import com.gutseeandpro.k1project.todo.ToDoDroid.repositories.UserDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.NonNull;

@Service
public class DataBaseConnector {

    private final UserDataRepository userDataRepository;

    public UserData getrecord(@NonNull final String userID) {
        final Optional<UserData> optional = userDataRepository.findById(userID);
        if(optional.isPresent()) {
            return optional.get();
        }
        return UserData.builder().build();
    }

    public void saveData(@NonNull final UserData userData) {
        final Optional<UserData> optional = userDataRepository.findById(userData.getUserId());
        final List<String> data = new LinkedList<>();
        if(optional.isPresent()) {
            UserData userDataInDb = optional.get();
            data.addAll(userDataInDb.getTodoData());
        }
        data.addAll(userData.getTodoData());
        userDataRepository.save(UserData.builder().todoData(data)
                .userId(userData.getUserId()).build());
    }

    public void deleteRecord(@NonNull final String userID) {
        userDataRepository.deleteById(userID);
    }

    @Autowired
    public DataBaseConnector(@NonNull final UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }
}
