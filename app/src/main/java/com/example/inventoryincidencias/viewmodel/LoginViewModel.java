package com.example.inventoryincidencias.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventoryincidencias.data.repository.UserRepository;
import com.example.inventoryincidencias.utils.CommonUtils;

import java.util.regex.Pattern;

/**
 * Esta clase contiene:
 * 1) Los métodos que garantizan que se cumple las reglas de negocio
 * 2) Los datos de la vista que quiero que se actualicen automaticamente y se mantengan
 *    durante el ciclo de vida de la Activity/Fragment (UI Controller)
 */

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    // Los siguientes LiveData tendrán observadores en la Activity/Fragment y se debe implementar un método de obtención
    private MutableLiveData<Boolean> errorEmailEmpty;
    private MutableLiveData<LoginResult> result;

    // el get() y el set() lo hace el DataBinding

    /**
     * Este método es el encargado de controlar todas las reglas de negocio se cumplen.
     * Si es correcto -> consulta al repositorio.
     */
    public void validateCredentials(){

        if (TextUtils.isEmpty(email.getValue()))
            // Se tiene que crear un LIVE DATA que englobe todos las posibles alternativas
            result.setValue(LoginResult.EMAILEMPTY);
        else if (!Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches())
            result.setValue(LoginResult.EMAILFORMAT);
        else if (TextUtils.isEmpty(password.getValue()))
            result.setValue(LoginResult.PASSWORDEMPTY);
        else if (!CommonUtils.isPasswordValid(password.getValue()))
            result.setValue(LoginResult.PASSWORDFORMAT);

        // Se llama al método Login del repositorio
        else
            login();

    }

    /**
     * Este método comprueba si el usuario existe en la BD o FireBase y devuelve (en este caso)
     * un valor booleano
     */
    private void login() {
        if (UserRepository.getInstance().login(email.getValue(), password.getValue()))
            result.setValue(LoginResult.SUCCESS);
        else
            result.setValue(LoginResult.FAILURE);
    }

    /**
     * Método de obtención del LIVEDATA
     * @return
     */
    public MutableLiveData<LoginResult> getResult() {
        if (result==null)
            result=new MutableLiveData<>();
        return result;
    }
}
