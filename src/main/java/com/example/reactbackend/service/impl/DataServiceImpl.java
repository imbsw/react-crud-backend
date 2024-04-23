package com.example.reactbackend.service.impl;

import com.example.reactbackend.model.User;
import com.example.reactbackend.service.DataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    List<User> users = new ArrayList<User>(Arrays.asList(
            new User("test1","test1@.com","test1"),
            new User("test2","test2@.com","test2"),
            new User("test3","test3@.com","test3")));

    @Override
    public List<User> getUserList() {
        return users;
    }
}
