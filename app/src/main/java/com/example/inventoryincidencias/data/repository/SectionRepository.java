package com.example.inventoryincidencias.data.repository;

import com.example.inventoryincidencias.adapter.DependencyAdapter;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.data.model.Section;

import java.util.ArrayList;
import java.util.Collections;

public class SectionRepository {
    private ArrayList<Section> list;

    private static SectionRepository instance;

    private SectionRepository() {
        list = new ArrayList<>();
        //initialice();
    }

    public static SectionRepository getInstance(){
        if (instance == null)
            instance = new SectionRepository();
        return instance;
    }

    public ArrayList<Section> getList() {
        return list;
    }

    private void initialice() {
        list.add(new Section("Section1", "sct1", DependencyRepository.getInstance().getList().get(0)));
        list.add(new Section("Section2", "sct2", DependencyRepository.getInstance().getList().get(1)));
        Collections.sort(list);
    }
}
