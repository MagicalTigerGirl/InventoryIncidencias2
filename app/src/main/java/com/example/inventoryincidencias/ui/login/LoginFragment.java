package com.example.inventoryincidencias.ui.login;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_DashBoardFragment;
import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.ui.base.BaseFragment;
import com.example.inventoryincidencias.ui.preferences.UserPrefManager;

public class LoginFragment extends BaseFragment {

    public LoginFragment() {
    }

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // IMPORTANTE ESTAS 2 LÍNEAS
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult value) {
                switch (value) {
                    case EMAILEMPTY:
                        setEmailErrorEmpty();        // Método que mostrará el error en el TextInputLayout
                        break;
                    case EMAILFORMAT:
                        setEmailErrorFormat();
                        break;
                    case PASSWORDEMPTY:
                        setPasswordErrorEmpty();
                        break;
                    case PASSWORDFORMAT:
                        setPasswordErrorFormat();
                        break;
                    case FAILURE:
                        showError(getString(R.string.errorLogin));
                        break;
                    case SUCCESS:
                        showUserPref();
                        showDashBoard();
                        break;
                }
                Toast.makeText(getContext(), "Valor de Error "+value, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnRegistrar.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_SignUpFragment));
        //binding.btnLogin.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_DashBoardFragment));


        // Se establece el objeto loginTextWatcher a las vistas Editable
        binding.tieCorreoElectronico.addTextChangedListener(new LoginTextWatcher(binding.tieCorreoElectronico));
        binding.tiePassword.addTextChangedListener(new LoginTextWatcher(binding.tiePassword));
    }

    /**
     * Este método comprueba si el usuario ha seleccionado el checkbox Recuerdame
     * y se guarda la información del usuario.
     */
    private void showUserPref() {
        if (binding.checkBoxRecuerdame.isChecked())
            new UserPrefManager(getContext()).login(binding.tieCorreoElectronico.getText().toString());
    }

    //region Métodos ALTERNATIVAS y EXITO del viewModel
    // Establecer el error en el TextInputLayout Email
    private void setEmailErrorEmpty() {
        binding.tilCorreoElectronico.setError(getString(R.string.errorEmailEmpty));
        binding.tilCorreoElectronico.requestFocus();                             // El cursor se posicione donde está el error
    }

    private void setEmailErrorFormat() {
        binding.tilCorreoElectronico.setError(getString(R.string.errorEmailFormat));
        binding.tilCorreoElectronico.requestFocus();                             // El cursor se posicione donde está el error
    }

    private void setPasswordErrorEmpty() {
        binding.tilContrasena.setError(getString(R.string.errorPasswordEmpty));
        binding.tilContrasena.requestFocus();                                   // El cursor se posicione donde está el error
    }

    private void setPasswordErrorFormat() {
        binding.tilContrasena.setError(getString(R.string.errorPasswordFormat));
        binding.tilContrasena.requestFocus();
    }

    /**
     * El usuario ha hecho login y se muestra la pantalla principal de la aplicación
     */
    private void showDashBoard() {
        NavHostFragment.findNavController(LoginFragment.this).navigate(action_LoginFragment_to_DashBoardFragment);
    }
    //endregion

    //region Métodos que gestionan los errores del TextInputLayout
    private void clearEmailError(){
        binding.tilCorreoElectronico.setError(null);
    }

    private void clearPasswordError(){
        binding.tilContrasena.setError(null);
    }

    /**
     * Esta clase escucha los cambios de un componente texto
     * Se añade el constructor con un TextView para saber qué vista ha cambiado en el método afterTextChanged
     * */
    class LoginTextWatcher implements TextWatcher {

        private TextView textView;

        private LoginTextWatcher(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (textView.getId()){
                case R.id.tieCorreoElectronico:
                    // Ventaja de la clase interna, es que accede a los métodos de la clase que la contiene
                    clearEmailError();
                case R.id.tiePassword:
                    clearPasswordError();
            }

        }
    }
    //endregion

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}