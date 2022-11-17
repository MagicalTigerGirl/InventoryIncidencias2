package com.example.inventoryincidencias.data.repository;

import com.example.inventoryincidencias.data.model.Dependency;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository {
    private ArrayList<Dependency> list;

    private static DependencyRepository instance;

    private DependencyRepository() {
        list = new ArrayList<>();
        initialice();
    }

    public static DependencyRepository getInstance() {
        if (instance == null) {
            instance = new DependencyRepository();
        }
        return instance;
    }

    public ArrayList<Dependency> getList(){
        return list;
    }

    private void initialice(){
        list.add(new Dependency("Maria", "MML", "Hola", "imagen"));
        list.add(new Dependency("Juan", "JML", "Adios", "imagen2"));
        Collections.sort(list);
    }
}
