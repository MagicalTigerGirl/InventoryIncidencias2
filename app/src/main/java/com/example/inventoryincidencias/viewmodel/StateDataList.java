package com.example.inventoryincidencias.viewmodel;

public class StateDataList<T>{

    // Enumerado que contiene los diferentes estados de la vista
    public enum DataState{
        CREATED,
        LOADING,
        NODATA,
        SUCCESS,
        COMPLETE
    }
    private DataState state;
    // Los datos son de cualquier tipo <T>
    private T data;

    // Se crea el constructor con los valores iniciales que debe tener un objeto
    public void StateDateList(){
        this.state=DataState.CREATED;
        this.data=null;
    }

    // Se crea los métodos que devuelven el objeto StateDataList modificado según el estado
    public StateDataList<T> loading() {
        this.state = DataState.LOADING;
        this.data = null;
        return this;
    }

    public StateDataList<T> nodata() {
        this.state = DataState.NODATA;
        this.data = null;
        return this;
    }

    public StateDataList<T> success(T data) {
        this.state = DataState.SUCCESS;
        this.data = data;
        return this;
    }

    public StateDataList<T> complete() {
        this.state = DataState.COMPLETE;
        return this;
    }

    public DataState getState() {
        return state;
    }

    public T getData() {
        return data;
    }
}
