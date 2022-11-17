package com.example.inventoryincidencias.ui;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_DashBoardFragment;
import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.databinding.FragmentSplashBinding;
import com.example.inventoryincidencias.viewmodel.LoginResult;
import com.example.inventoryincidencias.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

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

        //viewModel.getErrorEmailEmpty().observe(this, new Observer<Boolean>() {
        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult value) {
                switch (value) {
                    case EMAILEMPTY:
                        setEmailEmpty();        // Método que mostrará el error en el TextInputLayout
                        break;
                }
                Toast.makeText(getContext(), "Valor de ErrorEmailEmpty"+value, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnRegistrar.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_SignUpFragment));
        //binding.btnLogin.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_DashBoardFragment));
    }

    private void setEmailEmpty() {
        binding.tilCorreoElectronico.setError(getString(R.string.errorEmailEmpty));
        binding.tilCorreoElectronico.requestFocus();                             // El cursor se posicione donde está el error
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}