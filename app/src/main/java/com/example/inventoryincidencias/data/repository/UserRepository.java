package com.example.inventoryincidencias.data.repository;

import com.example.inventoryincidencias.data.model.User;

import java.util.ArrayList;

public class UserRepository {
    private ArrayList<User> list;

    private static UserRepository instance;

    private UserRepository() {
        list = new ArrayList<>();
        initialice();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public ArrayList<User> getList() { return list; }

    public void initialice() {
        list.add(new User("mariamolinalorenzo@gmail.com", "26122003", "María", "Molina Lorenzo", 1));
        list.add(new User("juan.inma.maria@hotmail.com", "26122003", "Juan", "Molina Albalá", 2));
    }
}
