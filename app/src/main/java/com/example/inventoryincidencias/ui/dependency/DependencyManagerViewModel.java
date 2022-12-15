package com.example.inventoryincidencias.ui.dependency;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.data.repository.DependencyRepository;
import com.example.inventoryincidencias.utils.CommonUtils;

/**
 * Esta clase es la encargada del caso de uso Add y Edit
 */
public class DependencyManagerViewModel extends ViewModel {
    private MutableLiveData<DependencyManagerResult> result = new MutableLiveData<>();

    /**
     * Método get para inicializar el Observador de la vista
     */
    public MutableLiveData<DependencyManagerResult> getResult() {
        return result;
    }

    /**
     * Método que comprueba que los datos sean correctos y que se inserta la dependencia en el repositorio/BD
     */
    public void add(Dependency dependency) {
        if (validateName(dependency) && validateShortName(dependency)) {
            if (DependencyRepository.getInstance().add(dependency))
                result.setValue(DependencyManagerResult.SUCCESS);
            else
                result.setValue(DependencyManagerResult.FAILURE);
        }
    }

    /**
     * Método que comprueba  que los datos sean correctos y que se edite la dependencia en el repositorio/BD
     * */
    public void edit(Dependency dependency) {
        if (validateName(dependency))
        if (DependencyRepository.getInstance().edit(dependency))
            result.setValue(DependencyManagerResult.SUCCESS);
        else
            result.setValue(DependencyManagerResult.FAILURE);
    }

    /**
     * Método que comprueba si los campos son vacíos y cumplen las reglas de formato
     */
    private boolean validateName(Dependency dependency) {
        boolean validate = false;
        if (TextUtils.isEmpty(dependency.getName()))
            // Vista muestra un mensaje de error
            result.setValue(DependencyManagerResult.NAMEEMPTY);
        else validate = true;
        return validate;
    }

    public boolean validateShortName(Dependency dependency) {
        boolean validate = false;
        if (TextUtils.isEmpty(dependency.getShortName()))
            result.setValue(DependencyManagerResult.SHORTNAMEEMPTY);
        else if ((!CommonUtils.isShortNameValid(dependency.getShortName())))
            result.setValue(DependencyManagerResult.SHORTNAMEFORMAT);
        else validate = true;
        return validate;
    }
}
