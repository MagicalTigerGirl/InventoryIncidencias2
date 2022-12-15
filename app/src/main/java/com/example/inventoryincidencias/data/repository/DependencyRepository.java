package com.example.inventoryincidencias.data.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.inventoryincidencias.data.model.Dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
        list.add(new Dependency(2, "Maria", "MML", "Hola", "imagen"));
        list.add(new Dependency(3, "Juan", "JML", "Adios", "imagen2"));
        list.add(new Dependency(1, "Inma", "ILT", "Adios", "imagen3"));
        Collections.sort(list);
    }

    /**
     * Método que añade la dependencia al repositorio
     */
    public boolean add(Dependency dependency) {
        list.add(dependency);
        return true;
    }

    /**
     * Método que edita la dependencia al repositorio
     */
    public boolean edit(Dependency dependency) {
        list.set(list.indexOf(dependency),dependency);
        return true;
    }

    public void delete(Dependency dependency) {
        list.remove(dependency);
    }

    public ArrayList<Dependency> clone() {
        ArrayList<Dependency> cloneList = new ArrayList<>();
        Iterator<Dependency> iterator = list.iterator();
        while (iterator.hasNext()){
            Dependency d = iterator.next();
            cloneList.add(d.clone());
        }
        return cloneList;
    }
}
