package com.gutseeandpro.k1project.todo.ToDoDroid.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Document("UserData")
public class UserData {

    @Id
    private String userId;
    
    @Builder.Default
    private List<String> todoData = new LinkedList<>();

}
