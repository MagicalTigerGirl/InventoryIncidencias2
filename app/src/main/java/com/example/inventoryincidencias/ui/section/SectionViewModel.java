package com.example.inventoryincidencias.ui.section;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventoryincidencias.data.model.Section;
import com.example.inventoryincidencias.data.repository.SectionRepository;

import java.util.ArrayList;

public class SectionViewModel extends ViewModel {
    private MutableLiveData<SectionResult> state;
    private MutableLiveData<ArrayList<Section>> data;

    public MutableLiveData<SectionResult> getState() {
        if (state == null)
            state = new MutableLiveData<>();
        return state;
    }

    public void setState(MutableLiveData<SectionResult> state) {
        this.state = state;
    }

    public MutableLiveData<ArrayList<Section>> getData() {
        if (data == null)
            data = new MutableLiveData<>();
        return data;
    }

    public void setData(MutableLiveData<ArrayList<Section>> data) {
        this.data = data;
    }

    public void getList() {
        ArrayList<Section> list = new ArrayList<>();

        // Vamos a comprobar los diferentes estado en los que se puede encontrar la vista
        state.setValue(SectionResult.LOADING);
        list = SectionRepository.getInstance().getList();

        if (list.isEmpty())
            state.setValue(SectionResult.NODATA);
        else
        {
            // Pedimos los datos al Repository
            data.setValue(list);
            state.setValue(SectionResult.SUCCESS);
        }
    }
}
