package com.example.inventoryincidencias.ui.dependency;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.data.repository.DependencyRepository;
import com.example.inventoryincidencias.viewmodel.StateLiveDataList;

import java.util.ArrayList;

/**
 * Clase que gestiona las reglas de negocio (Entidad) de la lista ListDependency
 */
public class DependencyListViewModel extends ViewModel {

    private MutableLiveData<String> user;
    private StateLiveDataList<ArrayList<Dependency>> liveDataList = new StateLiveDataList<>();

    /**
     * Método que inicializar el objeto LiveDataList que contiene las posibles alternativas
     * del caso de uso listar a través de un objeto StateDataList
     * @return
     */
    public StateLiveDataList<ArrayList<Dependency>> getLiveDataList() {
        //if (liveDataList==null)
        //    liveDataList = new StateLiveDataList<>();
        return liveDataList;
    }

    public void getDataList() {
        // 2. Muestra un progressBar en la vista porque los datos se están cargando
        liveDataList.setLoading();

        // 3. Pedimos los datos al repositorio
        ArrayList<Dependency> list = DependencyRepository.getInstance().getList();

        // 3.1 No DATA
        if (list.isEmpty())
            liveDataList.setNoData();
        // 3.2 Caso de éxito
        else
            liveDataList.setSuccess(list);
        // 4. Se completa el caso de uso
        //liveDataList.setComplete();                   problema de sincronismo
    }
}
